package moreno.cazalilla.jesusmaria.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import moreno.cazalilla.jesusmaria.R;
import moreno.cazalilla.jesusmaria.models.PokemonData;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<PokemonData> listPokemon;


    public PokemonAdapter(List<PokemonData> list) {
        this.listPokemon = list;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_pokedex, parent, false);

        return new PokemonViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        PokemonData pokemon = listPokemon.get(position);


        holder.nameTextView.setText(pokemon.getName());
        //holder.pesoTextView.setText(pokemon.getApiDetails().getWeight());
        //holder.orderTextView.setText(pokemon.getApiDetails().getId());
        //holder.alturaTextView.setText(pokemon.getApiDetails().getHeight());


        if (pokemon.getApiDetails() != null && pokemon.getApiDetails().getSprites() != null) {
            String imageUrl = pokemon.getApiDetails().getSprites().getFront_default();
            if (imageUrl != null) {
                Glide.with(holder.itemView.getContext())
                        .load(imageUrl)
                        .into(holder.imageView);
            }
        }

    }


    @Override
    public int getItemCount() {
        return listPokemon.size();
    }/*if (pokemon.getApiDetails() != null && pokemon.getApiDetails().getSprites() != null) {
            String imageUrl = pokemon.getApiDetails().getSprites().getFront_default();
            if (imageUrl != null) {
                Glide.with(holder.itemView.getContext())
                        .load(imageUrl)
                        .into(holder.imageView);
            }
        }*/


    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView;
        TextView orderTextView;
        TextView pesoTextView;
        TextView alturaTextView;
        TextView tipoTextView;


        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            nameTextView = itemView.findViewById(R.id.name);
            orderTextView = itemView.findViewById(R.id.order);
            pesoTextView = itemView.findViewById(R.id.peso);
            alturaTextView = itemView.findViewById(R.id.altura);
            tipoTextView = itemView.findViewById(R.id.tipo);

        }
    }
}


