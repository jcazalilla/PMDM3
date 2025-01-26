package moreno.cazalilla.jesusmaria;


import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {

    private List<PokedexData> listPokedex;


    public PokedexAdapter(List<PokedexData> list) {
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

        PokedexData pokemon = listPokedex.get(position);
        holder.nameTextView.setText(pokemon.getName());
        holder.urlTextView.setText(pokemon.getUrl());

        // Configura el OnClickListener para grabar elemento en firestore
        holder.itemView.setOnClickListener(v -> {

            //recoger datos de panntalla para la colección
            PokedexData pokedexData = new PokedexData(holder.nameTextView.getText().toString(),
                    holder.urlTextView.getText().toString());

            //instancia de la base de datos de Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            //agregar datos a la colección firestore
            db.collection("pokemon").add(pokedexData)
                    .addOnSuccessListener(runnable -> {
                        Toast.makeText(v.getContext(), holder.nameTextView.getText() + " guardado.", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(runnable ->
                            Toast.makeText(v.getContext(), "Error al guardar Pokédex.", Toast.LENGTH_SHORT).show()
                    );
        });

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
