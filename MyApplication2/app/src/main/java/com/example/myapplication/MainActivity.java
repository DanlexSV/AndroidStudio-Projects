package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText numE1, numE2, correoDestino, mensaje;
    Spinner Asuntos;
    ArrayList<String> asunto;
    Adaptador adaptador;
    String asuntoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numE1 = findViewById(R.id.num1);
        numE2 = findViewById(R.id.num2);
        correoDestino = findViewById(R.id.correoDestino);
        mensaje = findViewById(R.id.mensaje);

        Button sumButton = findViewById(R.id.suma);
        Button subtractButton = findViewById(R.id.resta);
        Button multiplyButton = findViewById(R.id.multi);
        Button divideButton = findViewById(R.id.divi);
        Button enviarEmailButton = findViewById(R.id.enviarEmail);

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion("+");
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion("-");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion("*");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion("/");
            }
        });


        Asuntos = findViewById(R.id.asunto);

        asunto = new ArrayList<>();
        asunto.add("Cancelar Pedido");
        asunto.add("Enviar Nuevo Modelo");
        asunto.add("Agregar Nuevo Pedido");
        asunto.add("Modificar Direccion");

        adaptador = new Adaptador(this, R.layout.spinner, asunto);
        Asuntos.setAdapter(adaptador);

        Asuntos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adaptador.toggleSelection(position);
                ArrayList<String> optionSelect = adaptador.getAsunto();

                asuntoTotal = TextUtils.join(", ", optionSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button Correo = findViewById(R.id.enviarEmail);
        Correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarCorreo(correoDestino, asuntoTotal, mensaje);
            }
        });

    }

    public void enviarCorreo(TextView correos, String asuntoS, TextView mensaje) {
        String correoS = correos.getText().toString();
        String[] direccionCorreos = {correoS};

        String mensajeS = mensaje.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"))
                .putExtra(Intent.EXTRA_EMAIL, direccionCorreos)
                .putExtra(Intent.EXTRA_SUBJECT, asuntoS)
                .putExtra(Intent.EXTRA_TEXT, mensajeS);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    private void calcularOperacion(String operador) {
        String num1Str = numE1.getText().toString();
        String num2Str = numE2.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        showToast("Error: No se puede dividir por cero");
                        return;
                    }
                    break;
                default:
                    showToast("Operador no válido");
                    return;
            }

            showToast("Resultado: " + resultado);
        } else {
            showToast("Por favor, ingresa ambos números");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}