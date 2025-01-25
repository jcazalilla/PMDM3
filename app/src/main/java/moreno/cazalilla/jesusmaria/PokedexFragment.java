package moreno.cazalilla.jesusmaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moreno.cazalilla.jesusmaria.databinding.FragmentPokedexBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;

    private List<PokemonData> listPokedex;
    private TextView nameEdittext;
    private TextView urlEdittext;


    public View onCreate(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPokedexBinding.inflate(inflater, container, false);

        //inicializamos RecycleView y el adaptador
        RecyclerView recyclerView = binding.recyclerViewPokedex;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PokedexAdapter adapter = new PokedexAdapter(listPokedex);
        recyclerView.setAdapter(adapter);


        //llamada a RETROFIT
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
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);
        Call<List<PokemonData>> call = pokemonAPI.getPokemons();


        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<List<PokemonData>> call, Response<List<PokemonData>> response) {
                if (response.isSuccessful()) {
                    listPokedex = response.body();

                    for (PokemonData l: listPokedex){
                        nameEdittext.setText(l.getName());
                        urlEdittext.setText(l.getUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PokemonData>> call, Throwable t) {
                nameEdittext.setText(t.getMessage());
            }
        });
    }

}
