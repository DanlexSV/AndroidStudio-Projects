package com.example.ejemplo1videojuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class LienzoCanva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienzo_canva);

        Lienzo fondo = new Lienzo(this);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.addView(fondo);

    }

    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(100, 255, 255, 255);
            int ancho = canvas.getWidth();
            float alturaRectangulo = 40;

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.your_name);

            Paint paint = new Paint();

            paint.setARGB(100, 35, 106, 163);
            float rectLeft = 10;
            float rectTop = 10;
            float rectRight = ancho - 10;
            float rectBottom = alturaRectangulo;
            canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, paint);

            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            float radioCirculo = alturaRectangulo / 2;
            float centroX = (rectRight + rectLeft) / 2; // Centro X del rectángulo
            float centroY = rectBottom + radioCirculo; // Centro Y del rectángulo + radio del círculo
            canvas.drawCircle(centroX, centroY, radioCirculo, paint);

            paint.setStrokeWidth(3);
            // Posicionar el Bitmap en el centro abajo del círculo
            float bitmapLeft = centroX - bitmap.getWidth() / 2;
            float bitmapTop = centroY;
            canvas.drawBitmap(bitmap, bitmapLeft, bitmapTop, null);
        }
    }
}