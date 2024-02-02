package com.example.projectogrupal.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.example.projectogrupal.R;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        EditText correoDestino = view.findViewById(R.id.correoDestino);
        EditText  asunto = view.findViewById(R.id.asunto);
        EditText  mensaje = view.findViewById(R.id.mensaje);
        EditText  ubicacion = view.findViewById(R.id.ubicacion);

        Button enviarB = view.findViewById(R.id.enviarEmail);
        enviarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarCorreo(correoDestino, asunto, mensaje);
            }
        });

        Button abrirMaps = view.findViewById(R.id.mapsB);
        abrirMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUbicacion(ubicacion);
            }
        });

        return view;
    }

    public void buscarUbicacion (TextView textView) {
        String Tubicacion = textView.getText().toString();
        Tubicacion = Tubicacion.replace(" ", "+");

        Uri goIntentUri = Uri.parse("geo:0,0?q=" + Tubicacion);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, goIntentUri);

        if (mapIntent.resolveActivity(getContext().getPackageManager()) != null)
            startActivity(mapIntent);
    }

    public void enviarCorreo(TextView correos, TextView asunto, TextView mensaje) {
        String correoS = correos.getText().toString();
        String[] direccionCorreos = {correoS};

        String asuntoS = asunto.getText().toString();
        String mensajeS = mensaje.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"))
                .putExtra(Intent.EXTRA_EMAIL, direccionCorreos)
                .putExtra(Intent.EXTRA_SUBJECT, asuntoS)
                .putExtra(Intent.EXTRA_TEXT, mensajeS);

        if (intent.resolveActivity(getContext().getPackageManager()) != null)
            startActivity(intent);
    }

}

