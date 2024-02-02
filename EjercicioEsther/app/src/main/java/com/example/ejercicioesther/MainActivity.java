package com.example.ejercicioesther;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ubicacion = findViewById(R.id.ubicacion);

        Button abrirMaps = findViewById(R.id.mapsB);
        abrirMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUbicacion(ubicacion);
            }
        });
    }

    public void buscarUbicacion(EditText editText) {
        String Ubicacion = editText.getText().toString();
        Ubicacion = Ubicacion.replace(" ", "+");

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Ubicacion );
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}