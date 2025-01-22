package moreno.cazalilla.jesusmaria.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import moreno.cazalilla.jesusmaria.adapters.PokemonsAdapter;
import moreno.cazalilla.jesusmaria.databinding.FragmentPokemonsBinding;
import moreno.cazalilla.jesusmaria.models.PokemonData;




public class PokemonsFragment extends Fragment {

    private FragmentPokemonsBinding binding;

    private RecyclerView recyclerView;
    private PokemonsAdapter adapter;
    private List<PokemonData> listPokemon;





    public View onCreate(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {







        binding = FragmentPokemonsBinding.inflate(inflater, container, false);

        //inicializamos RecycleView y el adaptador
        recyclerView = binding.recyclerViewPokemons;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonsAdapter(listPokemon);
        recyclerView.setAdapter(adapter);

        //cargarmos los datos del xml
        loadPokemos();
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadPokemos() {
    }


}
