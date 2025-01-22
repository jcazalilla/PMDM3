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

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import moreno.cazalilla.jesusmaria.adapters.PokedexAdapter;
import moreno.cazalilla.jesusmaria.databinding.FragmentPokedexBinding;
import moreno.cazalilla.jesusmaria.models.PokemonData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


//interfaz que permite a retrofit saber los endpoints
interface PokemonAPI {
    @GET("pokemon/?limit=50")
    Call getPokemons();
}


public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;

    private RecyclerView recyclerView;
    private PokedexAdapter adapter;
    private List<PokemonData> listPokedex;


    public View onCreate(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPokedexBinding.inflate(inflater, container, false);

        //inicializamos RecycleView y el adaptador
        recyclerView = binding.recyclerViewPokedex;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokedexAdapter(listPokedex);
        recyclerView.setAdapter(adapter);


        //RETROFIT
        loadPokedex();
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadPokedex() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                )).build();
        PokemonAPI pokemonApi = retrofit.create(PokemonAPI.class);
        Call call = pokemonApi.getPokemons();


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    ArrayList pokemonList = response.body().getResults();
                    View recyclerView = findViewById(R.id.item_list);
                    assert recyclerView != null;
                    setupRecyclerView((RecyclerView) recyclerView, pokemonList);
                } else {
                    Toast.makeText(this, "Error al cargar Pokédex", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO
            }
        });

    }


}
