package com.example.proyectorestaurante;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class ModificarProducto extends AppCompatActivity {

    private ArrayList<String> typeFood = new ArrayList<>(Arrays.asList("Carnes", "Ensaladas", "Entrantes", "Pastas", "Postres"));
    Spinner tipoComida, namePlato;
    String colection, document;
    EditText precioText;
    AdaptadorTypeFood adapter;
    ArrayList<String> platillos = new ArrayList<>();
    ArrayAdapter<String> listaPlatillos;
    private FirebaseFirestore data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        if (savedInstanceState != null) {
            colection = savedInstanceState.getString("colection", "");
            document = savedInstanceState.getString("document", "");
            String precioTextValue = savedInstanceState.getString("precioText", "");
            precioText.setText(precioTextValue);
        }

        data = FirebaseFirestore.getInstance();

        tipoComida = findViewById(R.id.tipoComidaM);
        namePlato = findViewById(R.id.platoModi);
        precioText = findViewById(R.id.precioNuevoM);

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

    public void modificarPlato(View view) {
        DocumentReference updateDocument = data.collection(colection).document(document);
        String precio = precioText.getText().toString() + " €";

        updateDocument
                .update("precio", precio)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        Toast.makeText(ModificarProducto.this, "Precio Modificado!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

        precioText.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guardar datos importantes antes de la recreación de la actividad
        outState.putString("colection", colection);
        outState.putString("document", document);
        outState.putString("precioText", precioText.getText().toString());
    }
}