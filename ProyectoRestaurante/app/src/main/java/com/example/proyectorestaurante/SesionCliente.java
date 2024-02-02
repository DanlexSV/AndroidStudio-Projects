package com.example.proyectorestaurante;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class SesionCliente extends AppCompatActivity {

    private ArrayList<String> typeFood = new ArrayList<>(Arrays.asList("Carnes", "Ensaladas", "Entrantes", "Pastas", "Postres"));
    Spinner spinner;
    ListView lista;
    AdaptadorCliente adaptador;
    String colection;
    ArrayList<String> platillos = new ArrayList<>();
    ArrayAdapter<String> listaPlatillos;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_cliente);

        database = FirebaseFirestore.getInstance();

        spinner = findViewById(R.id.tipoComida);
        lista = findViewById(R.id.listaComida);

        adaptador = new AdaptadorCliente(this, R.layout.spinner_cliente, typeFood);
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colection = typeFood.get(position);
                database.collection(colection)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    platillos.clear();

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String platoName = document.getId();
                                        platillos.add(platoName);
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                    listaPlatillos.notifyDataSetChanged();
                                } else
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listaPlatillos = new ArrayAdapter<>(this, R.layout.lista_comida_cliente, platillos);
        lista.setAdapter(listaPlatillos);
    }
}