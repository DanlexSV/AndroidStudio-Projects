package com.example.ejemplojuego2parte2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder holder;
    private RunGame bucle;
    int touchX, touchY, index;
    private ArrayList<Toque> toques;
    boolean hayToque;
    private static final String TAG = RunGame.class.getSimpleName();

    public Game(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        setOnTouchListener(this);
        toques = new ArrayList<>();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        getHolder().addCallback(this); //Para interceptar eventos de la surfaceview
        bucle = new RunGame(getHolder(),this);
        setFocusable(true); //Para poder capturar eventos
        bucle.start(); //Arrancamos el hilo
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d(TAG,"Juego destruido");
        boolean retry = true;
        while (retry) {
            bucle.end();
            try {
                bucle.join();
                retry = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        index = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchX = (int) event.getX(index);
                touchY = (int) event.getY(index);
                hayToque = true;

                synchronized (this) {
                    toques.add(index, new Toque(touchX, touchY, index));
                }
                Log.d("toque", "pulsado dedo " + index);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                synchronized (this) {
                    toques.remove(index);
                }
                Log.d("toques", "soltado " + index);
                break;
            case MotionEvent.ACTION_UP:
                synchronized (this) {
                    toques.remove(index);
                }
                Log.d("toque", "soltado ultimo " + index);
                hayToque = false;
                break;
        }
        return true;
    }

    public void Update() {

    }

    public void Render(Canvas canvas) {

    }

}