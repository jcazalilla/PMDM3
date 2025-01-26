package moreno.cazalilla.jesusmaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.firebase.ui.auth.AuthUI;

import moreno.cazalilla.jesusmaria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    NavController navController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura la Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.navHostFragment);

        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            NavigationUI.setupWithNavController(binding.navView, navController);
            NavigationUI.setupActionBarWithNavController(this, navController);
        }

        binding.navView.setOnItemSelectedListener(this::selectedBottomMenu);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.navigation_pokedex ||
                    destination.getId() == R.id.navigation_pokemon ||
                    destination.getId() == R.id.navigation_ajustes) {
                //Para las pantallas de los tabs, que no aparezca la flecha de atrás
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                {
                    //Si se navega a una pantalla donde se desea mostrar la flecha atrás, habilitada
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Maneja el clic en la flecha de atrás
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }


    private boolean selectedBottomMenu(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_pokedex) {
            navController.navigate(R.id.navigation_pokedex);
        } else if (menuItem.getItemId() == R.id.nav_pokemons) {
            navController.navigate(R.id.navigation_pokemon);
        } else {
            navController.navigate(R.id.navigation_ajustes);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //infla el menú
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

    public boolean onOptionItemSelected(MenuItem item) {

        //gestiona el click en el item de información
        if (item.getItemId() == R.id.acerca) {
            showInfoDialog();//muestra diálogo
        }
        return true;
    }

    private void showInfoDialog() {
        //crea diálogo de información
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_acerca_de)
                .setMessage(R.string.acerca_de)
                .setPositiveButton(R.string.accept, null)
                .show();
    }

    //=================================================================================================
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





/*

        //===============================================================================================
        //datbase firestore
        private void saveDataToFirestore (View view){
            //recoger datos de panntalla para la colección
            PokemonData pokemonData = new PokemonData(binding.nameEditText.getText().toString(), );

            //instancia de la base de datos de Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            //agregar datos a la colección firestore
            db.collection("player").add(pokemonData)
                    .addOnSuccessListener(runnable ->
                            Toast.makeText(this, "Registro guardado.", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(Runnable ->
                            Toast.makeText(this, "Error al guardar registro.", Toast.LENGTH_SHORT).show()
                    );
        }

        // lectura de datos de la colección
        private void readAllDataFirestore (View view) {
            //instancia de la base de datos de Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Task<QuerySnapshot> player = db.collection("player").get().addOnSuccessListener(runnable ->
                            Toast.makeText(this, "lectura correcta de la colección.", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(Runnable ->
                            Toast.makeText(this, "fallo en la letura de la colección", Toast.LENGTH_SHORT).show());

        }

*/
