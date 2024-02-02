package com.example.ejercicio1layouttabulares;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.gridlayout.widget.GridLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    GridLayout gridMinas;
    int gridSize = 8, numbombas = 10, clickD = 0, filF, colF;
    ImageButton face, buttonM;
    int[][] valores, generaBomba;
    ArrayList<Integer> position;
    TextView numBomb, Timer;
    boolean haExplotado = false;
    Thread TimerH;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = findViewById(R.id.faceMinas);
        gridMinas = findViewById(R.id.gridMinas);

        gridMinas.setRowCount(gridSize);
        gridMinas.setColumnCount(gridSize);

        numBomb = findViewById(R.id.numBanderas);
        Timer = findViewById(R.id.contador);
        TimerH = new Thread(new Tiempo(haExplotado, Timer));


        generaBomba = new int[gridSize][gridSize];
        position = new ArrayList<>();

        for (int i = 0; i < gridSize * gridSize; i++)
            position.add(i);
        Collections.shuffle(position);

        for (int i = 0; i < numbombas; i++) {//xd
            int posicion = position.get(i);
            int fil = posicion / gridSize;
            int col = posicion % gridSize;
            generaBomba[fil][col] = 1;
        }


        valores = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++) {
                if (generaBomba[i][j] == 0) {
                    int numBombasAlrededor = 0;
                    for (int x = -1; x <= 1; x++)
                        for (int y = -1; y <= 1; y++)
                            if (i + x >= 0 && i + x < gridSize && j + y >= 0 && j + y < gridSize)
                                if (generaBomba[i + x][j + y] == 1)
                                    numBombasAlrededor++;
                    valores[i][j] = numBombasAlrededor;
                } else
                    valores[i][j] = -1;
            }

        // Inicia el Hilo en el Menu cuando se cree
        TimerH.start();

        buttonM = crearBotones();
        int valorCasilla = valores[filF][colF];
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                face.setImageResource(R.drawable.smile_face);
                switch (valorCasilla) {
                    case -1:
                        buttonM.setImageResource(R.drawable.bomba);
                        face.setImageResource(R.drawable.death_face);
                        haExplotado = true;
                        break;
                    case 0:
                        buttonM.setImageResource(0);
                        break;
                    case 1:
                        buttonM.setImageResource(R.drawable.numero1);
                        break;
                    case 2:
                        buttonM.setImageResource(R.drawable.numero2);
                        break;
                    case 3:
                        buttonM.setImageResource(R.drawable.numero3);
                        break;
                    case 4:
                        buttonM.setImageResource(R.drawable.numero4);
                        break;
                    case 5:
                        buttonM.setImageResource(R.drawable.numero5);
                        break;
                    case 6:
                        buttonM.setImageResource(R.drawable.numero6);
                        break;
                    case 7:
                        buttonM.setImageResource(R.drawable.numero7);
                        break;
                    case 8:
                        buttonM.setImageResource(R.drawable.numero8);
                        break;
                }
            }
        });

        buttonM.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                face.setImageResource(R.drawable.face_shocked);
                clickD++;
                if (clickD > 3)
                    clickD = 1;

                switch (clickD){
                    case 1:
                        buttonM.setImageResource(R.drawable.bandera_mina);
                        break;
                    case 2:
                        buttonM.setImageResource(R.drawable.cuadro_pregunta);
                        break;
                    case 3:
                        buttonM.setImageResource(R.drawable.cuadro_inicial);
                        break;
                }
                return false;
            }
        });


    }

    public ImageButton crearBotones() {
        ImageButton buttonM = new ImageButton(this);
        for (int fil = 0; fil < gridSize; fil++)
            for (int col = 0; col < gridSize; col++) {
                int IDunica = fil * gridSize + col;
                int color = Color.parseColor("#00FFFFFF");
                filF = IDunica / gridSize;
                colF = IDunica % gridSize;

                buttonM = new ImageButton(this);
                buttonM.setId(IDunica);
                buttonM.setImageResource(R.drawable.cuadro_inicial);
                buttonM.setScaleType(ImageView.ScaleType.FIT_XY);
                buttonM.setBackgroundTintList(ColorStateList.valueOf(color));
                buttonM.setPadding(0, 0, 0, 0);
                buttonM.setContentDescription("Diseño Boton");

                int sizeInDp = 40;
                float scale = getResources().getDisplayMetrics().density;
                int sizeInPixels = (int) (sizeInDp * scale);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = sizeInPixels;
                params.height = sizeInPixels;
                buttonM.setLayoutParams(params);

                gridMinas.addView(buttonM);

            }
        return buttonM;
    }

    class Tiempo implements Runnable {
        private boolean Stop;
        TextView contador = findViewById(R.id.contador);
        public Tiempo(boolean b, TextView textView) {
            this.Stop = b;
            this.contador = textView;
        }

        @Override
        public void run() {
            int i = 1;
            while (!Stop) {
                final int count = i;
                contador.post(new Runnable() {
                    @Override
                    public void run() {
                        contador.setText(String.valueOf(count));
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }
}
