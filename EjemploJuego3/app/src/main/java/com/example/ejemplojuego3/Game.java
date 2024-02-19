package com.example.ejemplojuego3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private RunGame bucle;
    private static final int MAX_IMAGES_BACKGROUND = 2;
    Bitmap[] images = new Bitmap[MAX_IMAGES_BACKGROUND];
    int[] recursos_imagenes = {R.drawable.honkai, R.drawable.genshin};
    int yCurrentImage, yNextImage;
    int current_image = 0, next_image = 1;
    private static final String TAG = RunGame.class.getSimpleName();

    public Game(Context context) {
        super(context);

        holder=getHolder();
        holder.addCallback(this);

        yCurrentImage = 0;
        yNextImage = -MainActivity.altoPantalla;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        cargaBackground();
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

    public void Update() {
        updateBackground();
    }

    public void Render(Canvas canvas) {
        canvas.drawBitmap(images[current_image], 0, yCurrentImage, null);
        canvas.drawBitmap(images[next_image], 0, yNextImage, null);
    }

    public void cargaBackground() {
        for (int i = 0; i < MAX_IMAGES_BACKGROUND; i++) {
            Bitmap fondo = BitmapFactory.decodeResource(getResources(), recursos_imagenes[i]);
            if (images[i] == null)
                images[i] = fondo.createScaledBitmap(fondo, MainActivity.anchoPantalla,
                        MainActivity.altoPantalla, true);
            fondo.recycle();
        }
    }

    public void updateBackground() {
        yCurrentImage += 10;
        yNextImage += 10;

        if (yCurrentImage > MainActivity.altoPantalla) {
            Log.d("carga", "Imagen1 dentro");

            if (current_image == MAX_IMAGES_BACKGROUND -1)
                current_image = 0;
            else
                current_image++;

            if (next_image == MAX_IMAGES_BACKGROUND -1)
                next_image = 0;
            else
                next_image++;

            yCurrentImage = 0;
            yNextImage = -MainActivity.altoPantalla;
        }
    }
}
