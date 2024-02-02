package com.example.prueba1;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextContraseña;
    private Button buttonEntrar, buttonSignIn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonSignIn = findViewById(R.id.buttonSignIn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editTextUsuario.getText().toString();
                String contraseña = editTextContraseña.getText().toString();

                // Muestra el nombre del usuario y la contraseña en un Toast
                Toast.makeText(MainActivity.this, "Usuario: " + usuario + "\nContraseña: " + contraseña, Toast.LENGTH_SHORT).show();

                mAuth.createUserWithEmailAndPassword(usuario, contraseña);

                // Borra el contenido de los campos de texto después de mostrar el Toast
                editTextUsuario.setText("");
                editTextContraseña.setText("");
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString();
                String contraseña = editTextContraseña.getText().toString();

                mAuth.signInWithEmailAndPassword(usuario, contraseña)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    Toast.makeText(MainActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();                                    //updateUI(null);
                                }
                            }
                        });

                // Borra el contenido de los campos de texto después de mostrar el Toast
                editTextUsuario.setText("");
                editTextContraseña.setText("");

                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

                Map<String, Object> datas1 = new HashMap<>();
                datas1.put("name", "San Francisco");
                datas1.put("state", "CA");
                datas1.put("country", "USA");
                datas1.put("capital", false);
                datas1.put("population", 860000);
                datas1.put("region", Arrays.asList("west_coast", "norcal"));
                db.collection("cities").document("SF").set(datas1);


                CollectionReference cities = db.collection("cities");

                Map<String, Object> data2 = new HashMap<>();
                data2.put("name", "Los Angeles");
                data2.put("state", "CA");
                data2.put("country", "USA");
                data2.put("capital", false);
                data2.put("population", 3900000);
                data2.put("regions", Arrays.asList("west_coast", "socal"));
                cities.document("LA").set(data2);

                Map<String, Object> data3 = new HashMap<>();
                data3.put("name", "Washington D.C.");
                data3.put("state", null);
                data3.put("country", "USA");
                data3.put("capital", true);
                data3.put("population", 680000);
                data3.put("regions", Arrays.asList("east_coast"));
                cities.document("DC").set(data3);

                Map<String, Object> data4 = new HashMap<>();
                data4.put("name", "Tokyo");
                data4.put("state", null);
                data4.put("country", "Japan");
                data4.put("capital", true);
                data4.put("population", 9000000);
                data4.put("regions", Arrays.asList("kanto", "honshu"));
                cities.document("TOK").set(data4);

                Map<String, Object> data5 = new HashMap<>();
                data5.put("name", "Beijing");
                data5.put("state", null);
                data5.put("country", "China");
                data5.put("capital", true);
                data5.put("population", 21500000);
                data5.put("regions", Arrays.asList("jingjinji", "hebei"));
                cities.document("BJ").set(data5);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
