package moreno.cazalilla.jesusmaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.firestore.FirebaseFirestore;

import moreno.cazalilla.jesusmaria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NavController navController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // binding.btnCerrarSesion.setOnClickListener(this::logOutSession);

        //binding.btnGarbarDatos.setOnClickListener(this::saveDataToFirestore);


        //navcontroller
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            NavigationUI.setupWithNavController(binding.navView, navController);
            NavigationUI.setupActionBarWithNavController(this, navController);
        }

        binding.navView.setOnItemSelectedListener(this::selectedBottonMenu);

    }

    private boolean selectedBottonMenu(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_pokemons)
            navController.navigate(R.id.navigation_pokemons);
        else
        if (menuItem.getItemId() == R.id.nav_pokedex)
            navController.navigate(R.id.navigation_pokedex);
        else
            navController.navigate(R.id.navigation_ajustes);
        return true;
    }



  /*  //datbase firestore
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
*/

    // session login
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