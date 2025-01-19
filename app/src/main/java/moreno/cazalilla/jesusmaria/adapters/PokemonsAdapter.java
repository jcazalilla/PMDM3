package moreno.cazalilla.jesusmaria.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moreno.cazalilla.jesusmaria.R;
import moreno.cazalilla.jesusmaria.models.PokemonData;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonsAdapter.PokemonsViewHolder> {

    private List<PokemonData> listPokemon;

    public PokemonsAdapter(List<PokemonData> list){
        this.listPokemon=list;
    }


    @NonNull
    @Override
    public PokemonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview,parent,false);
        return new PokemonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonsAdapter.PokemonsViewHolder holder, int position) {

        PokemonData pokemon = listPokemon.get(position);
        holder.nameTextView.setText(pokemon.getName());

        //cargar la imagen de pokémon (simulado con un recurso compartido)
        int imageResId = holder.itemView.getContext().getResources()
                .getIdentifier(pokemon.getPhoto(),"drawable",holder.itemView.getContext()
                        .getPackageName());

        holder.imageImageView.setImageResource(imageResId);

    }

    @Override
    public int getItemCount() {
        return listPokemon.size();
    }

    public static class PokemonsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageImageView;
        public PokemonsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            imageImageView=itemView.findViewById(R.id.image);
        }
    }
}
