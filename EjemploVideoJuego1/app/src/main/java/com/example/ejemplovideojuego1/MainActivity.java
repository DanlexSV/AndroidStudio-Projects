package com.example.ejemplovideojuego1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Game(this));
    }

    /* METER ESTO EN LA CLASE GAME (DIAPOSITIVA VIDEOJUEGOS3)
        @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                hayToque = true;
                Log.d( "toque", "down");
                break;
            case MotionEvent.ACTION_UP:
                hayToque = false;
                Log.d("toque", "up");
                break;
        }

        touchX = (int) event.getX();
        touchY = (int) event.getY();

        Log.d(TAG, touchX + ", " + touchY);

        return true;
    }
     */
}