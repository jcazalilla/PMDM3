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
import com.google.firebase.firestore.FirebaseFirestore;

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
            if (destination.getId() == R.id.navigation_pokedex ){
                //Para las pantallas de los tabs, que no aparezca la flecha de atrás
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setTitle("Lista Pokédex");
            }else if(destination.getId() == R.id.navigation_pokemon ){
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setTitle("Pokémons caputurados");
            }else if(destination.getId() == R.id.navigation_ajustes) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setTitle("Ajustes");
            }else{
                    //Si se navega a una pantalla donde se desea mostrar la flecha atrás, habilitada
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

