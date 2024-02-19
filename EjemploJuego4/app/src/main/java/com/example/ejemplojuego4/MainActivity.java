package com.example.ejemplojuego4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowMetrics;

public class MainActivity extends AppCompatActivity {

    public static int anchoPantalla, altoPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculatesizescreen();
        setContentView(new Game(this));
    }

    public void calculatesizescreen() {
        WindowManager windowManager = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
        WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
        altoPantalla = currentWindowMetrics.getBounds().height();
        anchoPantalla=currentWindowMetrics.getBounds().width();

        Log.d("medidas", "alto: " + altoPantalla);
        Log.d("medidas", "ancho: " + anchoPantalla);
    }
}