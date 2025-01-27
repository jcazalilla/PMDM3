package moreno.cazalilla.jesusmaria;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.util.List;

import moreno.cazalilla.jesusmaria.models.PokemonData;
import moreno.cazalilla.jesusmaria.models.Types;

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
        if (pokemon.getHeight()!=null && pokemon.getOrder()!=null && pokemon.getHeight()!=null){

            holder.pesoTextView.setText(pokemon.getWeight());
            holder.orderTextView.setText(pokemon.getOrder());
            holder.alturaTextView.setText(pokemon.getHeight());
        }



        if (pokemon.getApiDetails() != null && pokemon.getApiDetails().getSprites() != null) {
            String imageUrl = pokemon.getApiDetails().getSprites().getOther().getHome().getFront_default();
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
    }


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


