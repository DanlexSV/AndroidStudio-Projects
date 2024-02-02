package com.example.proyectorestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SesionEmpleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_empleado);
    }

    public void agregarProducto(View view) {
        Intent intent = new Intent(this, AgregarProducto.class);
        startActivity(intent);
    }

    public void modificarProducto(View view) {
        Intent intent = new Intent(this, ModificarProducto.class);
        startActivity(intent);
    }

    /*public void consultarProducto(View view) {

    }*/

    public void eliminarProducto(View view) {
        Intent intent = new Intent(this, EliminarProducto.class);
        startActivity(intent);
    }
}