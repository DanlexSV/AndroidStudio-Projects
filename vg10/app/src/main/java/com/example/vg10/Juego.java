package com.example.vg10;

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

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder holder;
    private BucleJuego bucle;
    private static final String TAG=Juego.class.getSimpleName();

    int touchX, touchY;
    boolean hayToque;

    private ArrayList<Toque> toques;
    int index;
    Control[] controles =new Control[3];

    public Juego(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        hayToque=false;
        setOnTouchListener(this);
        toques= new ArrayList<Toque>();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        getHolder().addCallback(this); //Para interceptar eventos de la surfaceview
        bucle=new BucleJuego(getHolder(),this);
        setFocusable(true); //Para poder capturar eventos
        bucle.start(); //Arrancamos el hilo
        cargaControles();
    }

    private void cargaControles() {
        controles[0]=new Control (getContext(),0,MainActivity.altoPantalla/5*4);
        controles[0].cargar(R.drawable.arrow_left);
        controles[0].nombre="IZQUIERDA";
        controles[1]=new Control (getContext(),controles[0].ancho(),MainActivity.altoPantalla/5*4);
        controles[1].cargar(R.drawable.arrow_up);
        controles[1].nombre="ARRIBA";
        controles[2]=new Control (getContext(),controles[0].ancho()*2,MainActivity.altoPantalla/5*4);
        controles[2].cargar(R.drawable.arrow_right);
        controles[2].nombre="DERECHA";
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d(TAG,"Juego destruido");
        boolean retry=true;
        while (retry) {
            bucle.fin();
            try {
                bucle.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            retry=false;
        }
    }

    public void actualizar() {
        for (Control c:controles) {
            if (c.pulsado)
                Log.d("Pulsados","El "+c.nombre);
        }

    }

    public void renderizar(Canvas canvas) {
        Paint mypaint=new Paint();
        canvas.drawColor(Color.GREEN);
        mypaint.setStyle(Paint.Style.STROKE);
        mypaint.setColor(Color.BLACK);
        mypaint.setTextSize(30);
        if (hayToque) {
            synchronized (this) {
                for (Toque t:toques) {
                    canvas.drawCircle(t.x, t.y, 100, mypaint);
                    canvas.drawText(t.index+"",t.x,t.y,mypaint);
                }
            }
        }
        for (Control c:controles)
            c.dibujar(canvas,mypaint);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        index=event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchX=(int) event.getX(index);
                touchY=(int) event.getY(index);
                hayToque=true;
                synchronized (this) {
                    toques.add(index,new Toque(touchX,touchY,index));
                }
                for (Control c:controles)
                    c.comprobarPulsado(touchX,touchY);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                synchronized (this) {
                    toques.remove(index);
                }
                for (Control c:controles)
                    c.compruebaSoltado(toques);
                break;
            case MotionEvent.ACTION_UP:
                synchronized (this) {
                    toques.remove(index);
                }
                hayToque=false;
                for (Control c:controles)
                    c.compruebaSoltado(toques);
                break;
        }


        return true;
    }
}
