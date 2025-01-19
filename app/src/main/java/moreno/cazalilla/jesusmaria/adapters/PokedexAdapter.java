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

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {
    private List<PokemonData> listPokedex;

    public PokedexAdapter(List<PokemonData> list){
        this.listPokedex=list;
    }

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview,parent, false);

        return new PokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexAdapter.PokedexViewHolder holder, int position) {

        PokemonData pokedex = listPokedex.get(position);
        holder.nameTextView.setText(pokedex.getName());

        //cargar la imagen (simulado con un recurso drawable)
        int imageResId = holder.itemView.getContext().getResources()
                .getIdentifier(pokedex.getPhoto(),"drawable",holder.itemView
                        .getContext().getPackageName());

        holder.imageImageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return listPokedex.size();
    }

    public class PokedexViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageImageView;
        public PokedexViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.name);
            imageImageView=itemView.findViewById(R.id.image);
        }
    }
}
