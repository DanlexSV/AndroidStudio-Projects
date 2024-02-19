package com.example.vg10;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class BucleJuego extends Thread {

    private final static int MAX_FPS=30;
    private final static int MAX_FRAMES_SALTADOS=5;
    private final static int TIEMPO_FRAME=1000/MAX_FPS;
    private Juego vg;
    public boolean juegoEnEjecucion=true;
    private static final String TAG=Juego.class.getSimpleName();
    private SurfaceHolder sh;

    BucleJuego(SurfaceHolder sh, Juego vg) {
        this.vg=vg;
        this.sh=sh;

    }

    @Override
    public void run() {
        Canvas canvas;
        long tiempoComienzo;
        long tiempoDiferencia;
        int tiempoDormir;
        int framesASaltar;

        tiempoDormir=0;

        while (juegoEnEjecucion) {
            canvas = null;
            try {
                canvas = this.sh.lockCanvas();
                synchronized (sh) {
                    tiempoComienzo = System.currentTimeMillis();
                    framesASaltar = 0;
                    vg.actualizar();
                    vg.renderizar(canvas);
                    tiempoDiferencia = System.currentTimeMillis() - tiempoComienzo;
                    tiempoDormir = (int) (TIEMPO_FRAME - tiempoDiferencia);
                    if (tiempoDormir > 0) {
                        try {
                            Thread.sleep(tiempoDormir);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (tiempoDormir < 0 && framesASaltar < MAX_FRAMES_SALTADOS) {
                        vg.actualizar();
                        tiempoDormir += TIEMPO_FRAME;
                        framesASaltar++;
                    }
                }
            } finally {
                if (canvas!=null)
                    sh.unlockCanvasAndPost(canvas);
            }
            Log.d(TAG,"Nueva iteración");
        }
    }

    public void fin() {
        juegoEnEjecucion=false;
    }

}
