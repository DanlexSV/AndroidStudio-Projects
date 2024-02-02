package com.example.restaurantegrupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private String correo = "danilibra15@hotmail.com";
    private String password = "dani2003sv";
    private FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance();
    }

    public void CrearUsuario(View view) {
        user.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Creacion de usuario correcta", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    }
                });

        
    }

    public void EntrarUser(View view) {
        user.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Inicio de sesion correcta", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void CrearBD(View v) {
        Intent intent = new Intent(this, InsertData.class);
        startActivity(intent);
    }

    public void ModifyDB(View view) {
        Intent intent = new Intent(this, ModifyData.class);
        startActivity(intent);
    }
}