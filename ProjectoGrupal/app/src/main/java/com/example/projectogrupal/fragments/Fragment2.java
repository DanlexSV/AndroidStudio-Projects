package com.example.projectogrupal.fragments;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.example.projectogrupal.R;

public class Fragment2 extends Fragment {

    private static final int NOTIFICATION_ID = 1;
    private static final int PERMISSION_REQUEST_CODE = 123;
    private TextView textView;

    public Fragment2() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        // Obtener referencia al TextView
        textView = view.findViewById(R.id.text);

        Button accesoPermiso = view.findViewById(R.id.permisoButton);
        accesoPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarPermiso();
            }
        });

        Button Notificacion = view.findViewById(R.id.abrirNotify);
        Notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearNotificacion("Arconte de Fontaine", "Nuevo personaje Hydro de espada ligera, 5 estrellas.");
            }
        });

        return view;
    }

    public void modificarContenido() {
        // Aquí implementa la lógica para modificar el contenido de Fragment1
        // Puedes acceder a las vistas dentro del fragmento y realizar los cambios necesarios
        if (textView != null)
            textView.setText("Fragmento 2 modificado");
    }

    public void crearNotificacion (String titulo, String contenido) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "channel_id")
                .setSmallIcon(R.drawable.icon_furina)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_furina))
                .setContentTitle(titulo)
                .setContentText("Nuevo personaje hydro de espada ligera")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contenido))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        RemoteViews customView = new RemoteViews(getContext().getPackageName(), R.layout.notify_model);
        customView.setTextViewText(R.id.Titulo, titulo);
        customView.setTextViewText(R.id.Contenido, contenido);

        builder.setCustomBigContentView(customView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(requireActivity().NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(requireActivity().NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void solicitarPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    mostrarMensaje("Es necesario conceder permisos para acceder al almacenamiento.");
            } else {
                mostrarMensaje("Permiso al almacenamiento ya concedido");
                activarWifi();
            }
        } else {
            mostrarMensaje("No se requieren permisos en versiones anteriores a M");
            activarWifi();
        }
    }

    private void activarWifi() {
        WifiManager wifiManager = (WifiManager) requireContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            if (!wifiManager.isWifiEnabled()) {
                abrirConfiguracionWifi();
            } else {
                mostrarMensaje("WiFi ya está habilitado");
            }
        } else {
            mostrarMensaje("WiFi no es compatible en este dispositivo");
        }
    }

    private void abrirConfiguracionWifi() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show();
    }
    private void mostrarMensaje1(String mensaje) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                mostrarMensaje1("Permiso concedido");
            else
                mostrarMensaje1("Permiso denegado");
        }
    }
}
