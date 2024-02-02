package com.example.proyectorestaurante;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    EditText correoText, paswdText;
    Switch empleSwitch;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        correoText = findViewById(R.id.editTextText);
        paswdText = findViewById(R.id.editTextTextPassword);

        empleSwitch = findViewById(R.id.switch1);

    }

    // Metodo para hacer un Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void crearUsuario(View view) {
        mAuth.createUserWithEmailAndPassword(correoText.getText().toString(), paswdText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "Creacion de usuario correcta");

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            String typeUser = empleSwitch.isChecked() ? "empleado" : "cliente";

                            // Asignamos un nombre al usuario dependiendo si es cliente o empleado
                            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(typeUser)
                                    .build();

                            // Actualizamos el nuevo usuario
                            user.updateProfile(changeRequest)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                                showToast(typeUser + " agregado!");
                                            }
                                        }
                                    });
                        }
                        else {
                            Log.d(TAG, "Error detectado!", task.getException());
                            showToast("Error al crear el usuario");
                        }

                        correoText.setText("");
                        paswdText.setText("");
                        empleSwitch.setChecked(false);
                    }
                });
    }

    public void iniciarSesion(View view) {
        mAuth.signInWithEmailAndPassword(correoText.getText().toString(), paswdText.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                        correoText.setText("");
                        paswdText.setText("");
                        empleSwitch.setChecked(false);
                    }
                });

        String ID = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
            ID = user.getDisplayName();

        if (ID.equals("cliente")) {
            Intent intent = new Intent(this, SesionCliente.class);
            startActivity(intent);
        } else if (ID.equals("empleado")) {
            Intent intent = new Intent(this, SesionEmpleado.class);
            startActivity(intent);
        }

    }
}