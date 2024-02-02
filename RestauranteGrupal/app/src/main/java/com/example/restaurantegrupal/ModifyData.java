package com.example.restaurantegrupal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class ModifyData extends AppCompatActivity {

    private String precioModificado = "10.00 €", document = "Plato1";
    private FirebaseFirestore database;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_data);

        database = FirebaseFirestore.getInstance();

        textResult = findViewById(R.id.textResultado);
    }

    public void modificarPlato(View view) {
        DocumentReference updateDocument = database.collection("Pastas").document("Carbonara");

        updateDocument
                .update("precio", precioModificado)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        Toast.makeText(ModifyData.this, "Plato Modificado correctamente!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        Toast.makeText(ModifyData.this, "Error al Modificar el plato.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void borrarPlato(View view) {
        database.collection("Ejemplo").document(document)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        Toast.makeText(ModifyData.this, "Plato Eliminado!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error deleting document", e);
                        Toast.makeText(ModifyData.this, "Error al eliminar el plato", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void obtenerPlato(View view) {
        DocumentReference reference = database.collection("Carnes").document("Cordero asado");
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> data = document.getData();
                        StringBuilder formatoDocument = new StringBuilder();

                        for (Map.Entry<String, Object> entry: data.entrySet())
                            formatoDocument.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");

                        textResult.setText(formatoDocument.toString());
                    } else {
                        Log.d(TAG, "No encontrado el documento");
                        Toast.makeText(ModifyData.this, "No se pudo obtener el dato", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "La consulta fallo ", task.getException());
                    Toast.makeText(ModifyData.this, "Fallo al realizar la consulta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void consultaMultiple(View view) {

    }
}