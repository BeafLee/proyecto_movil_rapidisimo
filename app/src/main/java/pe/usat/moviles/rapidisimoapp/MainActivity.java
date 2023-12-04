package pe.usat.moviles.rapidisimoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import pe.usat.moviles.rapidisimoapp.util.LocationService;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sesion = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sesion.edit();
        String token = sesion.getString("token", "");
        final Intent intent;
        //Evaluamos si hay una sesión iniciada
        if (!token.equals("")) {
            //Hay una sesión activa
            /*Llamar al NavigationActivity*/
            intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);
        } else {
            /*Llamar al LoginActivity*/
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        /*
        // Verifica los permisos en tiempo de ejecución para versiones superiores a Android 6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permisos ya concedidos
                startLocationService();
            } else {
                // Solicitar permisos en tiempo de ejecución
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            // Versiones anteriores a Android 6.0, los permisos se conceden en el manifiesto
            startLocationService();
        }*/

        LocationService locationService = new LocationService(this.getApplicationContext(), this);
        // Inicia el servicio de ubicación new LocationService(this.getApplicationContext(), this).getClass()
        //startService(new Intent(this, LocationService.class));

        /*Cerrar el MainActivity*/
        MainActivity.this.finish();
    }

    /*
    private void startLocationService() {
        // Inicia el servicio de ubicación en segundo plano
        startService(new Intent(this, LocationService.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, inicia el servicio de ubicación
                startLocationService();
            } else {
                // Permiso denegado, muestra un mensaje o realiza acciones adicionales según tus necesidades
            }
        }
    }

     */
}