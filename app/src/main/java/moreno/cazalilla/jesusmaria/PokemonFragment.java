package moreno.cazalilla.jesusmaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import moreno.cazalilla.jesusmaria.databinding.FragmentPokemonBinding;
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

        // lectura de datos de la colección

        //instancia de la base de datos de Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener la colección "pokemon"
        db.collection("pokemon")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Limpiar la lista actual de Pokémon
                    listPokemon.clear();

                    // Recorrer los documentos recuperados
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Convertir el documento a un objeto PokemonData
                        PokemonData pokemon = document.toObject(PokemonData.class);
                        listPokemon.add(pokemon);
                    }

                    // Notificar al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();

                    // Mostrar un mensaje de éxito
                    Toast.makeText(getContext(), "Colección cargada con éxito.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Mostrar un mensaje de error
                    Toast.makeText(getContext(), "Fallo en la lectura de la colección: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

}
