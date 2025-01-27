package moreno.cazalilla.jesusmaria.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import moreno.cazalilla.jesusmaria.R;

public class AjustesFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        // Carga las preferencias desde el archivo XML
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }


}
