package pe.usat.moviles.rapidisimoapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;

import pe.usat.moviles.rapidisimoapp.LoginActivity;
import pe.usat.moviles.rapidisimoapp.NavigationActivity;
import pe.usat.moviles.rapidisimoapp.R;
import pe.usat.moviles.rapidisimoapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    MaterialButton btnCerrar;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnCerrar = view.findViewById(R.id.btnCerrar);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Eliminamos el token de sesion
                final SharedPreferences sesion = getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sesion.edit();
                editor.putString("token", "");

                editor.commit();

                /*Llamar al NavigationActivity*/
                final Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                /*Cerrar el MainActivity*/
                getActivity().finish();

            }
        });

        return view;
    }


}