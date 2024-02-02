package com.example.ejemplovideojuego1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private RunGame bucle;
    int x, y, contadorFrames;
    boolean hacia_abajo = true;
    private static final String TAG = RunGame.class.getSimpleName();

    public Game(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
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

    public void Update() {
        if (x > bucle.maxX)
            //si llega al final de la imagen vuelve??
            hacia_abajo = false;
        if (x == 0)
            hacia_abajo = true;
        if (hacia_abajo) {
            x++;
            y++;
        } else {
            x--;
            y--;
        }
        contadorFrames++;
    }

    public void Render(Canvas canvas) {

        if (canvas != null) {

            Paint mypaint = new Paint();
            mypaint.setStyle(Paint.Style.STROKE);

            canvas.drawColor(Color.MAGENTA);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.kaori3);
            float bmpInicialX = 0;
            float bmpInicialY = 0;
            canvas.drawBitmap(bmp, bmpInicialX + x, bmpInicialY + y, null);

            mypaint.setStrokeWidth(10);
            mypaint.setColor(Color.BLUE);

            float rectInicialX = 0;
            float rectInicialY = 0;
            canvas.drawRect(rectInicialX + x, rectInicialY + y, 300, 300, mypaint);

            float arcoInicialX = 0;
            float arcoInicialY = 0;
            RectF rectF = new RectF(arcoInicialX + x, arcoInicialY + y, 200, 120);

            canvas.drawOval(rectF, mypaint);
            mypaint.setColor(Color.BLACK);

            canvas.drawArc(rectF, 90, 45, true, mypaint);

            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setTextSize(40);

            float textoInicialX = 0;
            float textoInicialY = 0;
            canvas.drawText("Frames ejecutados: " + contadorFrames, textoInicialX, textoInicialY + y, mypaint);
        }
    }
}
