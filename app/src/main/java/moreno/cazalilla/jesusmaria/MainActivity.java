package moreno.cazalilla.jesusmaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.firestore.FirebaseFirestore;

import moreno.cazalilla.jesusmaria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCerrarSesion.setOnClickListener(this::logOutSession);

        binding.btnGarbarDatos.setOnClickListener(this::saveDataToFirestore);

    }

    private void saveDataToFirestore(View view) {
        PlayerData playerData = new PlayerData(binding.nameEdittext.getText().toString(),
                binding.descriptionEdittext.getText().toString());

        //instancia de la base de datos de Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("player").add(playerData)
                .addOnSuccessListener(runnable ->
                        Toast.makeText(this, "Registro guardado.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(Runnable ->
                        Toast.makeText(this, "Error al guardar registro.", Toast.LENGTH_SHORT).show()
                );
    }


    private void logOutSession(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> goToLogin());
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}