package com.example.proyectorestaurante;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class EliminarProducto extends AppCompatActivity {

    private ArrayList<String> typeFood = new ArrayList<>(Arrays.asList("Carnes", "Ensaladas", "Entrantes", "Pastas", "Postres"));
    Spinner tipoComida, namePlato;
    String colection, document;
    AdaptadorTypeFood adapter;
    ArrayList<String> platillos = new ArrayList<>();
    ArrayAdapter<String> listaPlatillos;
    private FirebaseFirestore data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);

        data = FirebaseFirestore.getInstance();

        tipoComida = findViewById(R.id.listaComidaE);
        namePlato = findViewById(R.id.platoElim);

        adapter = new AdaptadorTypeFood(this, R.layout.spinner_add_product, typeFood);
        tipoComida.setAdapter(adapter);

        tipoComida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colection = typeFood.get(position);
                data.collection(colection)
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
        namePlato.setAdapter(listaPlatillos);

        namePlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                document = platillos.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void borrarPlato(View view) {
        data.collection(colection).document(document)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        Toast.makeText(EliminarProducto.this, "Plato eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error deleting document", e);
                    }
                });
    }
}