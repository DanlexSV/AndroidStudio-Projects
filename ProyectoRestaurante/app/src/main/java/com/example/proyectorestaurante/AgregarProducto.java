package com.example.proyectorestaurante;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class AgregarProducto extends AppCompatActivity {

    private ArrayList<String> typeFood = new ArrayList<>(Arrays.asList("Carnes", "Ensaladas",
            "Entrantes", "Pastas", "Postres"));
    private ArrayList<String> typeAlergenos = new ArrayList<>(Arrays.asList("Ninguno", "Leche",
            "Huevos", "Cacahuates", "Frutos secos(Almendras, nueces, avellanas, etc.)", "Soja",
            "Trigo", "Pescado", "Mariscos", "Sésamo", "Mostaza", "Apio"));
    EditText newName, precio;
    CheckBox isAlergenos;
    LinearLayout contenido;
    Spinner spinTypeFood, spinAlergenos;
    AdaptadorTypeFood adapterType;
    AdaptadorAlergenos adapterAlergenos;
    private FirebaseFirestore database;
    boolean aBoolean = false;
    String colection, totalAlergenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        database =  FirebaseFirestore.getInstance();

        newName = findViewById(R.id.namePlatoNew);
        precio = findViewById(R.id.precioNuevoM);

        isAlergenos = findViewById(R.id.contieneAlergenos);
        contenido = findViewById(R.id.contentAlergenos);

        spinTypeFood = findViewById(R.id.tipoComidaAdd);
        spinAlergenos = findViewById(R.id.listAlergenos);

        isAlergenos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                aBoolean = true;
                contenido.setVisibility(View.VISIBLE);
            } else {
                aBoolean = false;
                contenido.setVisibility(View.INVISIBLE);
            }
        });

        adapterType = new AdaptadorTypeFood(this, R.layout.spinner_add_product, typeFood);
        spinTypeFood.setAdapter(adapterType);

        spinTypeFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colection = typeFood.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        adapterAlergenos = new AdaptadorAlergenos(this, R.layout.spinner_add_product, typeAlergenos);
        spinAlergenos.setAdapter(adapterAlergenos);

        spinAlergenos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapterAlergenos.toggleSelection(position);
                ArrayList<String> selectOptions = adapterAlergenos.getOptions();

                totalAlergenos = TextUtils.join(", ", selectOptions);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addProduct(View view) {
        String newProduc = newName.getText().toString();
        String precioS = precio.getText().toString() + " €";

        Caracteristicas carat = new Caracteristicas(precioS, aBoolean, totalAlergenos);

        database.collection(colection).document(newProduc).set(carat)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Plato ingresado con exito");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        precio.setText("");
        newName.setText("");

        Intent intent = new Intent(this, SesionEmpleado.class);
        startActivity(intent);
    }
}