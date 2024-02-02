package com.example.modelovideojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class VideoGame extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private RunJuego runJuego;
    private static final String TAG = VideoGame.class.getSimpleName();

    public VideoGame(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        getHolder().addCallback(this);
        runJuego = new RunJuego(getHolder(), this);
        setFocusable(true);
        runJuego.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d(TAG, "Juego destruido!");
        boolean retry = true;
        while (retry) {
            runJuego.end();
            try {
                runJuego.join();
                retry = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Update() {

    }

    public void Render(Canvas canvas) {

    }
}
