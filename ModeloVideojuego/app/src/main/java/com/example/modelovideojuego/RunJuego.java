package com.example.modelovideojuego;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class RunJuego extends Thread{

    private final static int MAX_FPS = 30;
    private final static int MAX_FRAMES_JUMPED = 5;
    private final static int TIME_FRAME = 1000 / MAX_FPS;
    private VideoGame game;
    public boolean gameExecution = true;
    private static final String TAG = VideoGame.class.getSimpleName();
    private SurfaceHolder holder;

    public RunJuego(SurfaceHolder holder, VideoGame game) {
        this.holder = holder;
        this.game = game;
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas;
        long timeComienzo;
        long timeDiferencia;
        int timeDormir = 0;
        int frameSaltar;

        while (gameExecution) {
            canvas = null;

            try {
                canvas = this.holder.lockCanvas();
                synchronized (holder) {
                    timeComienzo = System.currentTimeMillis();
                    frameSaltar = 0;

                    game.Update();
                    game.Render(canvas);

                    timeDiferencia = System.currentTimeMillis() - timeComienzo;
                    timeDormir = (int) (TIME_FRAME - timeDiferencia);

                    if (timeDormir > 0) {
                        try {
                            Thread.sleep(timeDormir);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    while (timeDormir < 0 && frameSaltar < MAX_FRAMES_JUMPED) {
                        game.Update();
                        timeDormir += TIME_FRAME;
                        frameSaltar++;
                    }
                }
            } finally {
                if (canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }
            Log.d(TAG, "Nueva iteración");
        }
    }

    public void end() {
        gameExecution = false;
    }
}
