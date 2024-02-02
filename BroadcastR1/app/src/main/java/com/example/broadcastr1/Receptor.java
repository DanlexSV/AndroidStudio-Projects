package com.example.broadcastr1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Receptor extends BroadcastReceiver {
    TextView tv;

    @Override
    public void onReceive(Context context, Intent intent) {
       /* Intent intento= new Intent(context, MainActivity.class);
        context.startActivity(intento);*/
        AppCompatActivity yourActivity = (AppCompatActivity) context;
        tv=(TextView) yourActivity.findViewById(R.id.textView);
        String cadena=intent.getStringExtra("extra");
        String cadena2=intent.getStringExtra("extra2");
        tv.setText(cadena+cadena2);
        Toast.makeText(context, "Recibido", Toast.LENGTH_SHORT).show();

    }
}
