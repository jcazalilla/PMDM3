package moreno.cazalilla.jesusmaria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<PokemonData> listPokemon;


    public PokemonAdapter(List<PokemonData> list) {
        this.listPokemon = list;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new PokemonViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        PokemonData pokemon = listPokemon.get(position);
        holder.nameTextView.setText(pokemon.getName());
        //holder.urlTextView.setText(pokemon.getUrl());

    }


    @Override
    public int getItemCount() {
        return listPokemon.size();
    }


    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView urlTextView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            urlTextView = itemView.findViewById(R.id.url);

        }
    }
}


