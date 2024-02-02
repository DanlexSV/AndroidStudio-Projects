package com.example.ejerciciodani;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    TextView tv, tvTit;
    Button bt;
    ArrayList<String> listaCaracteristicas;
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTit = (TextView)findViewById(R.id.idTVtitulo);
        lv = (ListView) findViewById(R.id.idLista);
        tv = (TextView) findViewById(R.id.idTxtV);
        bt = (Button) findViewById(R.id.idBT);

        listaCaracteristicas = new ArrayList<String>();
        listaCaracteristicas.add("Ataque de lejos");
        listaCaracteristicas.add("Ataque de cerca");
        listaCaracteristicas.add("Ataque consecutivo");
        listaCaracteristicas.add("Personaje de apoyo");
        listaCaracteristicas.add("Chica");
        listaCaracteristicas.add("Chico");
        listaCaracteristicas.add("Ulti variada");

        adaptador = new ArrayAdapter<String>(this,R.layout.lista, listaCaracteristicas);

        lv.setAdapter(adaptador);


    }

}