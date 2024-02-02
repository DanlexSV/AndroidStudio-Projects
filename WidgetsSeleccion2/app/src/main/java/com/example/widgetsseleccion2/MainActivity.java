package com.example.widgetsseleccion2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner Lista;
    HijaMain adapter;
    ArrayList<String> nombre;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] imagenes = new int[]{R.drawable.banderajapon, R.drawable.banderaecuador,
                R.drawable.banderaespania, R.drawable.banderareinounido, R.drawable.banderaitalia};

        nombre = new ArrayList<String>();
        nombre.add("Japon");
        nombre.add("Ecuador");
        nombre.add("España");
        nombre.add("Reino Unido");
        nombre.add("Italia");

        Lista = findViewById(R.id.SpinPaises);
        resultado = findViewById(R.id.textResultado);

        adapter = new HijaMain(this, R.layout.listapaises, nombre, imagenes);
        Lista.setAdapter(adapter);

        Lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultado.setText("Has seleccionado: " + nombre.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
