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


import java.util.ArrayList;
import java.util.List;

import moreno.cazalilla.jesusmaria.adaptador.PokedexAdapter;
import moreno.cazalilla.jesusmaria.api.PokedexAPI;
import moreno.cazalilla.jesusmaria.databinding.FragmentPokedexBinding;
import moreno.cazalilla.jesusmaria.models.PokedexData;
import moreno.cazalilla.jesusmaria.models.PokedexResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;
    private List<PokedexData> listPokedex;
    PokedexAdapter adapter;
    String BASE_URL = "https://pokeapi.co/api/v2/";




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        listPokedex = new ArrayList<>();


        //inicializamos RecycleView y el adaptador
        RecyclerView recyclerView = binding.recyclerViewPokedex;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokedexAdapter(listPokedex);
        recyclerView.setAdapter(adapter);


        loadPokedex();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void loadPokedex() {
        //llamada a RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokedexAPI pokedexAPI = retrofit.create(PokedexAPI.class);
        Call<PokedexResponse> call = pokedexAPI.getPokedex();


        call.enqueue(new Callback<PokedexResponse>() {

            @Override
            public void onResponse(Call<PokedexResponse> call, Response<PokedexResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    listPokedex.clear();
                    listPokedex.addAll(response.body().getResults());


                    adapter.notifyDataSetChanged();

                } else if (!response.isSuccessful()) {

                    Toast.makeText(getContext(), "Error al cargar lista de Pokédex", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokedexResponse> call, Throwable t) {
                if (getContext() != null)
                    Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
