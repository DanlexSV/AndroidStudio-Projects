package com.example.restaurantegrupal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertData extends AppCompatActivity {

    RadioButton isAlergenos;
    EditText precio;
    LinearLayout contenido;
    Spinner lista;
    ArrayList<String> alergenos;
    AdaptadorAlergeno adaptador;
    private FirebaseFirestore database;
    String alergenosTotal = null, nameDocument = "Plato2";
    Boolean alergenBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        database = FirebaseFirestore.getInstance();

        precio = findViewById(R.id.precio);
        isAlergenos = findViewById(R.id.alergenos);
        contenido = findViewById(R.id.contentAlergenos);
        lista = findViewById(R.id.listAlergenos);

        isAlergenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAlergenos.isChecked()) {
                    alergenBoolean = true;
                    contenido.setVisibility(View.VISIBLE);
                }
            }
        });

        alergenos = new ArrayList<>(Arrays.asList("Ninguno", "Leche", "Huevos", "Cacahuates",
                "Frutos secos(Almendras, nueces, avellanas, etc.)", "Soja", "Trigo", "Pescado",
                "Mariscos", "Sésamo", "Mostaza", "Apio"));

        adaptador = new AdaptadorAlergeno(this, R.layout.spinner_alergenos, alergenos);
        lista.setAdapter(adaptador);

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adaptador.toggleSelection(position);
                ArrayList<String> SelectOptions = adaptador.getOptions();

                alergenosTotal = TextUtils.join(", ", SelectOptions);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addProduct(View view) {

        String valorIngresado = precio.getText().toString() + " €";

        Caracteristicas caract = new Caracteristicas(valorIngresado, alergenBoolean, alergenosTotal);

        database.collection("Ejemplo").document(nameDocument).set(caract)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Plato ingresado con exito");
                        Toast.makeText(InsertData.this, "Plato ingreso con exito.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(InsertData.this, "Error al ingresar los datos.", Toast.LENGTH_SHORT).show();
                    }
                });

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}