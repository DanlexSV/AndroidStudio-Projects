package com.example.ejemplojuego4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    boolean hayToque;
    private ArrayList<Toque> toques;
    Control[] controls = new Control[3];
    private static final String TAG = RunGame.class.getSimpleName();


    public Game(Context context) {
        super(context);

        holder=getHolder();
        holder.addCallback(this);

        hayToque = false;
        setOnTouchListener(this);
        toques = new ArrayList<>();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        getHolder().addCallback(this); //Para interceptar eventos de la surfaceview
        bucle = new RunGame(getHolder(),this);
        setFocusable(true); //Para poder capturar eventos
        bucle.start(); //Arrancamos el hilo
        loadControls();
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
                for (Control c:controls)
                    c.checkPulsado(touchX, touchY);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                synchronized (this) {
                    toques.remove(index);
                }
                for (Control c:controls)
                    c.checkSoltado(toques);
                break;
            case MotionEvent.ACTION_UP:
                synchronized (this) {
                    toques.remove(index);
                }
                hayToque = false;
                for (Control c:controls)
                    c.checkSoltado(toques);
                break;
        }
        return true;
    }

    public void loadControls() {
        controls[0] = new Control(getContext(), 0, MainActivity.altoPantalla/5*4);
        controls[0].load(R.drawable.izquierda);
        controls[0].name = "IZQUIERDA";
        controls[1] = new Control(getContext(), controls[0].ancho(), MainActivity.altoPantalla/5*4);
        controls[1].load(R.drawable.arriba);
        controls[1].name = "ARRIBA";
        controls[2] = new Control(getContext(), controls[0].ancho()*2, MainActivity.altoPantalla/5*4);
        controls[2].load(R.drawable.derecha);
        controls[2].name = "DERECHA";
    }

    public void Update() {
        for (Control c:controls)
            if (c!= null && c.pulsado)
                Log.d("Check", "El " + c.name);
    }

    public void Render(Canvas canvas) {
        Paint myPaint = new Paint();
        canvas.drawColor(Color.BLUE);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setColor(Color.BLACK);
        myPaint.setTextSize(30);
        if (hayToque)
            synchronized (this) {
                for (Toque t:toques) {
                    canvas.drawCircle(t.x, t.y, 100, myPaint);
                    canvas.drawText(t.index + "", t.x, t.y, myPaint);
                }
            }
        for (Control c:controls)
            c.draw(canvas, myPaint);
    }
}
