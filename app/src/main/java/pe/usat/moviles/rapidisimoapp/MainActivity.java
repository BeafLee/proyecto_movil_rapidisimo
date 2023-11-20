package pe.usat.moviles.rapidisimoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton btnGo = findViewById(R.id.btnGoNav);
        btnGo.setOnClickListener(view -> {
            /*Llamar al NavigationActivity*/
            final Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);

            /*Cerrar el MainActivity*/
            MainActivity.this.finish();
        });

    }
}