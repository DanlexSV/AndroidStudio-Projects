package com.example.widgetsseleccion3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imagenes;
    ImageButton User;
    TextView text1, textF;
    ConstraintLayout PanelUser;
    EditText Nombre, Apellido, Correo, Password;
    Button Entrar;
    String SNombre, SApellido;
    LinearLayout Seleccion;
    Spinner Lista;
    Adaptador adaptador;
    ArrayList<String> nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imagenes = findViewById(R.id.ContenidoImagenes);
        User = findViewById(R.id.UserButton);
        text1 = findViewById(R.id.TextoInicial);
        textF = findViewById(R.id.textResultado);
        PanelUser = findViewById(R.id.PanelUser);

        Nombre = findViewById(R.id.Nombre);
        Apellido = findViewById(R.id.Apellido);
        Correo = findViewById(R.id.Correo);
        Password = findViewById(R.id.Password);
        Entrar = findViewById(R.id.EntrarBoton);

        Seleccion = findViewById(R.id.LayoutSpinner);

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.INVISIBLE);
                PanelUser.setVisibility(View.VISIBLE);
            }
        });

        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SNombre = Nombre.getText().toString();
                SApellido = Apellido.getText().toString();
                PanelUser.setVisibility(View.INVISIBLE);

                if (!SNombre.equals("") || !SApellido.equals("")) {
                    Toast.makeText(getBaseContext(), "Bienvenido de Regreso!", Toast.LENGTH_LONG).show();
                    Seleccion.setVisibility(View.VISIBLE);
                } else {
                    text1.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(), "No has iniciado sesion!", Toast.LENGTH_LONG).show();
                }
                Nombre.setText("");
                Apellido.setText("");
                Correo.setText("");
                Password.setText("");
            }
        });

        Lista = findViewById(R.id.ListaInstrumentos);

        int[] imagenes = new int[]{R.drawable.producto_1_music,
                R.drawable.producto_2_music, R.drawable.producto_3_music};

        nombre = new ArrayList<String>();
        nombre.add("Teclados portátiles");
        nombre.add("Guitarras, Bajos y Amplificadores");
        nombre.add("Mas intrumentos");

            adaptador = new Adaptador(this, R.layout.spinnerproductos, nombre, imagenes);
        Lista.setAdapter(adaptador);

        Lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adaptador.toggleSelection(position);
                ArrayList<String> opcionesSelect = adaptador.getOpciones();

                String opcionesTexto = TextUtils.join(", ", opcionesSelect);
                textF.setText("Has seleccionado: " + opcionesTexto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
