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

import moreno.cazalilla.jesusmaria.adapters.PokedexAdapter;
import moreno.cazalilla.jesusmaria.databinding.FragmentPokedexBinding;
import moreno.cazalilla.jesusmaria.models.PokemonData;

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

        //cargarmos los datos del xml
        loadPokedex();
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadPokedex() {
    }


}
