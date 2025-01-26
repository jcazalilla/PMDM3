package moreno.cazalilla.jesusmaria;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moreno.cazalilla.jesusmaria.databinding.FragmentPokedexBinding;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {

    private List<PokemonData> listPokedex;


    public PokedexAdapter(List<PokemonData> list) {
        this.listPokedex = list;
    }

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new PokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexAdapter.PokedexViewHolder holder, int position) {

        PokemonData pokemon = listPokedex.get(position);
        holder.nameTextView.setText(pokemon.getName());
        holder.urlTextView.setText(pokemon.getUrl());

    }


    @Override
    public int getItemCount() {
        return listPokedex.size();
    }


    public class PokedexViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView urlTextView;

        public PokedexViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            urlTextView = itemView.findViewById(R.id.url);

        }
    }
}
