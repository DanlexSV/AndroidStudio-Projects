package com.example.trabajogrupal1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText correoDestino = findViewById(R.id.correoDestino);
        EditText  asunto = findViewById(R.id.asunto);
        EditText  mensaje = findViewById(R.id.mensaje);
        EditText  ubicacion = findViewById(R.id.ubicacion);

        Button enviarB = findViewById(R.id.enviarEmail);
        enviarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarCorreo(correoDestino, asunto, mensaje);
            }
        });

        Button abrirMaps = findViewById(R.id.mapsB);
        abrirMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUbicacion(ubicacion);
            }
        });

        Button Notificacion = findViewById(R.id.abrirNotify);
        Notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearNotificacion("Arconte de Fontaine", "Nuevo personaje Hydro de espada ligera, 5 estrellas.");
            }
        });

    }

    public void buscarUbicacion (TextView textView) {
        String Tubicacion = textView.getText().toString();
        Tubicacion = Tubicacion.replace(" ", "+");

        Uri goIntentUri = Uri.parse("geo:0,0?q=" + Tubicacion);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, goIntentUri);

        if (mapIntent.resolveActivity(getPackageManager()) != null)
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

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    public void crearNotificacion (String titulo, String contenido) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.icon_furina)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_furina))
                .setContentTitle(titulo)
                .setContentText("Nuevo personaje hydro de espada ligera")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contenido))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.notify_model);
        customView.setTextViewText(R.id.Titulo, titulo);
        customView.setTextViewText(R.id.Contenido, contenido);

        builder.setCustomBigContentView(customView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
