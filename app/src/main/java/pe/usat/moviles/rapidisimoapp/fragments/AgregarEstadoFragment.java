package pe.usat.moviles.rapidisimoapp.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import pe.usat.moviles.rapidisimoapp.R;
import pe.usat.moviles.rapidisimoapp.response.GenericoResponse;
import pe.usat.moviles.rapidisimoapp.retrofit.ApiService;
import pe.usat.moviles.rapidisimoapp.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarEstadoFragment extends Fragment implements LocationListener {

    private int solicitudId, conductorId, vehiculoId;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    TextInputEditText txtLatitud, txtLongitud, txtObservacion;
    AutoCompleteTextView actvNombre;
    String txtNombreEstado = "";
    ImageButton btnUbicacion;
    MaterialButton btnRegistrar;

    private double latitude, longitude;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            solicitudId = getArguments().getInt("solicitudId");
            conductorId = getArguments().getInt("conductorId");
            vehiculoId = getArguments().getInt("vehiculoId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_estado, container, false);

        actvNombre = view.findViewById(R.id.txtNombre);
        txtLatitud = view.findViewById(R.id.txtLatitud);
        txtLongitud = view.findViewById(R.id.txtLongitud);
        txtObservacion = view.findViewById(R.id.txtObservacion);

        btnUbicacion = view.findViewById(R.id.btnUbicacion);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        actvNombre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtén la opción seleccionada por el usuario
                txtNombreEstado = (String) adapterView.getItemAtPosition(i);
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLatitud.setText(String.valueOf(latitude));
                txtLongitud.setText(String.valueOf(longitude));
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarEstado();
            }
        });

        listarEstados();
        ubicacion();

        return view;
    }

    private void registrarEstado() {
        ApiService apiService = RetrofitClient.createService();
        Call<GenericoResponse> call = apiService.registrarNuevoEstado(
                solicitudId,
                vehiculoId,
                conductorId,
                txtNombreEstado,
                latitude,
                longitude,
                txtObservacion.getText().toString()
        );
        call.enqueue(new Callback<GenericoResponse>() {
            @Override
            public void onResponse(Call<GenericoResponse> call, Response<GenericoResponse> response) {
                //TODO CORRECTO
                Toast.makeText(getContext(), "Estado registrado!", Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation);
                navController.popBackStack();

            }

            @Override
            public void onFailure(Call<GenericoResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error al registrar estado", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });

    }

    private void listarEstados() {
        // Define un conjunto de datos para el AutoCompleteTextView (puedes obtener estos datos de cualquier fuente)
        String[] listadoEstados = {"EN TRANSITO", "DETENIDO POR DESCANSO", "DETENIDO POR INTERRUPCIÓN", "FINALIZADO"};

        // Crea un adaptador y úsalo para llenar el AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, listadoEstados);
        actvNombre.setAdapter(adapter);

        // Deshabilita la entrada libre (para que solo pueda seleccionar de la lista desplegable)
        actvNombre.setInputType(0);

    }

    private void ubicacion() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        final Boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            new AlertDialog.Builder(getContext())
                    .setMessage("GPS es necesario para el registro del estado. Por favor habilítela.")
                    .setPositiveButton("Habilite GPS", (dialog, which) -> {
                        Intent propiedades = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(propiedades);
                    })
                    .show();
        }

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60 * 1, 0, this);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 1, 0, this);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ubicacion();
            } else {
                Toast.makeText(getContext(), "La localización es requerida en esta aplicación.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        txtLatitud.setText(String.valueOf(latitude));
        txtLongitud.setText(String.valueOf(longitude));
        Log.i("LOGS",  "Coordenadas del registro: " + latitude + " - " + longitude);
    }


}