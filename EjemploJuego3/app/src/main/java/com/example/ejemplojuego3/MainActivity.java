package com.example.ejemplojuego3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
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
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowMetrics currentWindow = windowManager.getCurrentWindowMetrics();
                altoPantalla = currentWindow.getBounds().height();
                anchoPantalla = currentWindow.getBounds().width();
            } else {
                // Manejo para versiones anteriores a Android 11
                altoPantalla = display.getHeight();
                anchoPantalla = display.getWidth();
            }

            Log.d("medidas", "alto: " + altoPantalla);
            Log.d("medidas", "ancho: " + anchoPantalla);
        }
    }
}