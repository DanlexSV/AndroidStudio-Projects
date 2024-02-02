package com.example.broadcastr1;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Receptor mireceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mireceptor = new Receptor();
        IntentFilter filter = new IntentFilter("com.example.broadcastr1.ACTION_EVENTO");
        registerReceiver(mireceptor, filter, RECEIVER_EXPORTED);

        Button but = findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aldarle(v);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        // unregisterReceiver(mireceptor);
    }

    public void aldarle (View v) {
        Intent intent = new Intent();
        intent.setAction("com.example.broadcastr1.ACTION_EVENTO");
        intent.putExtra("extra","pulsado");
        intent.putExtra("extra2"," otra vez");
        sendBroadcast(intent);
    }
}