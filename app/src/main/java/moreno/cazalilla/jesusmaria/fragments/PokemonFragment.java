package moreno.cazalilla.jesusmaria.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import moreno.cazalilla.jesusmaria.adaptador.PokemonAdapter;
import moreno.cazalilla.jesusmaria.api.PokemonAPI;
import moreno.cazalilla.jesusmaria.databinding.FragmentPokemonBinding;
import moreno.cazalilla.jesusmaria.models.PokemonData;
import moreno.cazalilla.jesusmaria.models.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonFragment extends Fragment {

    private FragmentPokemonBinding binding;
    private List<PokemonData> listPokemon;
    PokemonAdapter adapter;
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPokemonBinding.inflate(inflater, container, false);
        listPokemon = new ArrayList<>();

        //iniciamos RecyclerView y adaptador
        RecyclerView recyclerView = binding.recyclerViewPokemon;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonAdapter(listPokemon);
        recyclerView.setAdapter(adapter);

        //cargamos pokemon capturados
        loadPokemon();
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadPokemon() {

        //instancia de la base de datos de Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtenemos la colección "pokémon"
        db.collection("pokemon")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Limpiamos la lista actual de Pokémon
                    listPokemon.clear();

                    //para almacenar cada endpoint, cada nombre
                    List<String> pokemonsNames = new ArrayList<>();

                    // Recorremos los documentos recuperados, bucle foreach
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Convertimos el documento a un objeto PokemonData
                        PokemonData pokemon = document.toObject(PokemonData.class);
                        listPokemon.add(pokemon);

                        //guardamos los nombres para la API
                        pokemonsNames.add(pokemon.getName());

                        adapter.notifyDataSetChanged();

                        //hacemos retrofit a la API
                        solicitaApiPokemons(pokemonsNames);

                    }
                    Toast.makeText(getContext(), "Colección cargada con éxito.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(getContext(), "Fallo en la lectura de la colección: " +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void solicitaApiPokemons(List<String> pokemonsNames) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);
        //lista para almacenar los detalles de la api
        List<PokemonResponse> apiPokemonList = new ArrayList<>();

        //hacemos una solicitud a la API por cada nombre
        for (String name : pokemonsNames) {
            Call<PokemonResponse> call = pokemonAPI.getPokemons(name);


            call.enqueue(new Callback<PokemonResponse>() {

                @Override
                public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {

                    if (response.isSuccessful() && response.body() != null) {

                        apiPokemonList.add(response.body());

                        if (apiPokemonList.size() == pokemonsNames.size()) {
                            combinedData(apiPokemonList);
                        } else {
                            Toast.makeText(getContext(), "Error al cargar Pokémon", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PokemonResponse> call, Throwable t) {
                    if (getContext() != null)
                        Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        }
    }


    private void combinedData(List<PokemonResponse> apiPokemonList) {
        //combinamos datos de firestore y API
        for (int i = 0; i < listPokemon.size(); i++) {
            PokemonData firestorePokemon = listPokemon.get(i);
            PokemonResponse apiPokemon = apiPokemonList.get(i);

            firestorePokemon.setApiDetails(apiPokemon);
        }
        adapter.notifyDataSetChanged();
    }
}