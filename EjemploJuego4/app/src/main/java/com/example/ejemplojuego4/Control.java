package com.example.ejemplojuego4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Control {
    public boolean pulsado = false;
    public float coordenadaX, coordenadaY;
    private Bitmap image;
    private Context myContext;
    public String name;

    public Control (Context c, float x, float y) {
        coordenadaX = x;
        coordenadaY = y;
        myContext = c;
    }

    public int ancho() {
        return image.getWidth() / 5;
    }

    public int alto() {
        return image.getHeight() / 4;
    }

    public void load(int recurso) {
        image = BitmapFactory.decodeResource(myContext.getResources(), recurso);
    }

    public void draw(Canvas canvas, Paint paint) {
        Rect r1 = new Rect(0, 0, ancho(), alto());
        Rect r2 = new Rect((int) coordenadaX, (int) coordenadaY, (int) coordenadaX + ancho(),
                (int) coordenadaY + alto());
        canvas.drawBitmap(image, r1, r2, paint);
    }

    public void checkPulsado(int x, int y) {
        if (x > coordenadaX && x < coordenadaX + ancho()
                && y > coordenadaY && y < coordenadaY + alto())
            pulsado = true;
    }

    public void checkSoltado(ArrayList<Toque> list) {
        boolean aux = false;
        for (Toque t:list)
            if (t.x > coordenadaX && t.x < coordenadaX + ancho()
                    && t.y > coordenadaY && t.y < coordenadaY + alto())
                aux = true;
        if (!aux)
            pulsado = false;
    }
}
