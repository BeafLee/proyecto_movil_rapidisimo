package pe.usat.moviles.rapidisimoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

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

        /*Cerrar el MainActivity*/
        MainActivity.this.finish();


    }
}