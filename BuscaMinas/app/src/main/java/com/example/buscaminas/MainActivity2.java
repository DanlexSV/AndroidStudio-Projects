package com.example.buscaminas;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity2 extends AppCompatActivity {

    GridLayout gridRendirse;
    ImageButton button;
    int[] datosRecibidos;
    int dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solucionario);

        button = findViewById(R.id.restartButton);
        gridRendirse = findViewById(R.id.gridRendirse);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            datosRecibidos = bundle.getIntArray("datos");
            dp = intent.getIntExtra("dpImage", 0);
            if (datosRecibidos != null) {
                int gridSize = (int) Math.sqrt(datosRecibidos.length);
                gridRendirse.setRowCount(gridSize);
                gridRendirse.setColumnCount(gridSize);

                for (int fil = 0; fil < gridSize; fil++)
                    for (int col = 0; col < gridSize; col++) {
                        int valorNum = fil * gridSize + col;
                        int valorCasilla = datosRecibidos[valorNum];
                        int color = Color.parseColor("#00FFFFFF");

                        ImageButton buttonM = new ImageButton(this);
                        buttonM.setScaleType(ImageView.ScaleType.FIT_XY);
                        buttonM.setBackgroundTintList(ColorStateList.valueOf(color));
                        buttonM.setPadding(0, 0, 0, 0);
                        buttonM.setContentDescription("Diseño Boton");
                        buttonM.setEnabled(false);

                        int sizeInDp = dp;
                        float scale = getResources().getDisplayMetrics().density;
                        int sizeInPixels = (int) (sizeInDp * scale);
                        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                        params.width = sizeInPixels;
                        params.height = sizeInPixels;
                        buttonM.setLayoutParams(params);

                        switch (valorCasilla) {
                            case -1:
                                buttonM.setImageResource(R.drawable.bomba);
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

                        gridRendirse.addView(buttonM);
                    }
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridRendirse.removeAllViews();
                Toast.makeText(getApplicationContext(), "Cambiando de pantalla", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}