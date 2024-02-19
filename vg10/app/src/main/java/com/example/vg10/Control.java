package com.example.vg10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Control {
    public boolean pulsado=false;
    public float coordenada_x, coordenada_y;
    private Bitmap imagen;
    private Context mContexto;
    public String nombre;

    public Control (Context c, float x, float y) {
        coordenada_x=x;
        coordenada_y=y;
        mContexto=c;
    }

    public void cargar(int recurso) {
        imagen= BitmapFactory.decodeResource(mContexto.getResources(),recurso);
    }
    public void dibujar (Canvas c, Paint p) {
        Rect r1=new Rect(0,0,ancho(),alto());
        Rect r2=new Rect((int)coordenada_x,(int)coordenada_y,(int)coordenada_x+ancho(),(int)coordenada_y+alto());
        c.drawBitmap(imagen,r1,r2,p);
    }
    public void comprobarPulsado (int x, int y) {
        if (x>coordenada_x && x<coordenada_x+ancho() && y>coordenada_y && y<coordenada_y+alto())
            pulsado=true;
    }
    public void compruebaSoltado (ArrayList<Toque> lista) {
        boolean aux=false;
        for (Toque t:lista) {
            if (t.x>coordenada_x && t.x<coordenada_x+ancho() && t.y>coordenada_y && t.y<coordenada_y+alto())
                aux=true;
        }
        if (!aux)
            pulsado=false;
    }
    public int ancho() {
        return imagen.getWidth()/5;
    }
    public int alto(){
        return imagen.getHeight()/4;
    }
}
