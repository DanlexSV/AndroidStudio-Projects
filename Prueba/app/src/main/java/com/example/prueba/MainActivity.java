package com.example.prueba;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String nombre, apellidos, telefono, DNI;
    Button sus, next, cancel;
    EditText name, apellido, telef, dni;
    TextView comp;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sus = findViewById(R.id.Suscribir);
        next = findViewById(R.id.Next);
        cancel = findViewById(R.id.Cancel);

        name = findViewById(R.id.Nombre);
        apellido = findViewById(R.id.Apellidos);
        telef = findViewById(R.id.Telefono);
        dni = findViewById(R.id.DNI);

        comp = findViewById(R.id.finalMessage);
        layout = findViewById(R.id.Registro);

        sus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                sus.setVisibility(View.INVISIBLE);
                comp.setVisibility(View.INVISIBLE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                sus.setVisibility(View.VISIBLE);
                name.setText("");
                apellido.setText("");
                telef.setText("");
                dni.setText("");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                nombre = name.getText().toString();
                apellidos = apellido.getText().toString();
                telefono = telef.getText().toString();
                DNI = dni.getText().toString();

                layout.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                sus.setVisibility(View.VISIBLE);
                comp.setText("Bienvenido " + nombre + " " + apellidos + ", con DNI " + DNI + ", y numero de telefono " + telefono);
                comp.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(), "Registrado con Exito", Toast.LENGTH_LONG).show();

                name.setText("");
                apellido.setText("");
                telef.setText("");
                dni.setText("");
            }
        });

    }
}

