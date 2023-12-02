package pe.usat.moviles.rapidisimoapp.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pe.usat.moviles.rapidisimoapp.R;
import pe.usat.moviles.rapidisimoapp.model.Solicitud;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleSolicitudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleSolicitudFragment extends Fragment {

    private int solicitudId;
    private FloatingActionButton btnRegistrarEstado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            solicitudId = getArguments().getInt("solicitudId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_solicitud, container, false);

        btnRegistrarEstado = view.findViewById(R.id.btnRegistrarEstado);


        btnRegistrarEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("solicitudId", solicitudId);

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation);
                navController.navigate(R.id.agregarEstadoFragment, bundle);
            }
        });

        return view;
    }
}