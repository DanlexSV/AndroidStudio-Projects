package com.example.vg10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowMetrics;

public class MainActivity extends AppCompatActivity {

    public static int altoPantalla, anchoPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculartamanopantalla();
        setContentView(new Juego(this));
    }

    private void calculartamanopantalla() {
        WindowManager windowManager = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
        WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
        altoPantalla = currentWindowMetrics.getBounds().height();
        anchoPantalla=currentWindowMetrics.getBounds().width();
    }
}