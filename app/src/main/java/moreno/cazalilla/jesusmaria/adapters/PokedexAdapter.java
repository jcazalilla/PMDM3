package moreno.cazalilla.jesusmaria.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moreno.cazalilla.jesusmaria.R;
import moreno.cazalilla.jesusmaria.fragments.PokedexFragment;
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


        holder.mIdView.setText(Integer.toString(position));
        holder.mContentView.setText(mValues.get(position).getName());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mOnClickListener);


    }
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
            Pokemon item = mValues.get(index);

            Context context = view.getContext();
            Intent intent = new Intent(context, PokedexFragment.class);
            intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, index + 1);
            intent.putExtra(ItemDetailFragment.ARG_ITEM_NAME, item.getName());
            intent.putExtra(ItemDetailFragment.ARG_DESCRIPTION, item.getDescription());

            context.startActivity(intent);
        }
    };

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
