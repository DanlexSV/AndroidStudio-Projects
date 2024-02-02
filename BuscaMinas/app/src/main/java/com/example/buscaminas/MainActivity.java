package com.example.buscaminas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    GridLayout gridMinas, gridResultado;
    int gridSize, numbombas, finalnumBombas, contador = 0, selectSize = 0, selectMine = 0, selectDP = 0;
    ImageButton face, menuB, surrenderB;
    int[][] valores, generaBomba;
    ArrayList<Integer> position;
    TextView numBomb, Timer, TextDifficult;
    Handler handler = new Handler();
    Runnable actualizarContador = new Runnable() {
        @Override
        public void run() {
            contador++;
            Timer.setText(String.valueOf(contador));
            handler.postDelayed(this, 1000);
        }
    };
    AlertDialog.Builder builderReinicio, builderRendirse, builderIns;
    AlertDialog alertReinicio, alertRendirse, alertIns;
    Intent intent;
    Toolbar barraMenu;
    RadioButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = findViewById(R.id.faceMinas);
        menuB = findViewById(R.id.ButtonMenu);
        surrenderB = findViewById(R.id.surrenderB);

        gridMinas = findViewById(R.id.gridMinas);
        gridResultado = findViewById(R.id.gridResultado);

        TextDifficult = findViewById(R.id.selectDifficult);
        numBomb = findViewById(R.id.numBanderas);
        Timer = findViewById(R.id.contador);

        alertReinicio = crearDialogoReinicio();
        alertRendirse = crearDialogoRendirse();
        alertIns = crearDialogoInstructions();

        barraMenu = findViewById(R.id.menuGrande);
        setSupportActionBar(barraMenu);
        getSupportActionBar().setTitle("Buscaminas");

        //Llamar al Menu
        menuB.setOnClickListener(v -> showMenu(v));


        //Reiniciamos el juego
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertReinicio.show();
            }
        });

        
        //Boton para rendirse
        surrenderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertRendirse.show();
            }
        });

    }
    //Finaliza el onCreate()


    //Iniciar un contador del juego
    public void startContador() {
        stopContador();
        contador = 0;
        handler.postDelayed(actualizarContador, 1000);
    }



    //Detiene el contador y elimina el tiempo
    private void stopContador() {
        handler.removeCallbacks(actualizarContador);
    }



    //Proceso para crear las bombas en posicion Aleatoria
    public void bombAleatoria(int size, int mine) {
        gridSize = size;
        numbombas = mine;
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
    }



    //Proceso para asignar el numero de bombas al rededor de una posicion
    public void searchMines(int size) {
        gridSize = size;

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
    }



    //Crear un juego en base a la opcion escogida en el Menu
    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.easy) {
                restartGame();
                TextDifficult.setVisibility(View.INVISIBLE);
                face.setImageResource(R.drawable.smile_face);
                startGame(8, 10, 40);
                startContador();
                return true;
            } else if (id == R.id.medium) {
                restartGame();
                TextDifficult.setVisibility(View.INVISIBLE);
                face.setImageResource(R.drawable.smile_face);
                startGame(10, 12, 38);
                startContador();
                return true;
            } else if (id == R.id.hard) {
                restartGame();
                TextDifficult.setVisibility(View.INVISIBLE);
                face.setImageResource(R.drawable.smile_face);
                startGame(12, 14, 33);
                startContador();
                return true;
            } else {
                return false;
            }
        });

        popupMenu.show();
    }



    //Funcion de GameOver para mostrar el resultado
    public void gameOver(GridLayout gridLayout, int size, int dp) {
        gridSize = size;
        GridLayout gridActual = gridLayout;

        gridActual.setRowCount(gridSize);
        gridActual.setColumnCount(gridSize);

        for (int fil = 0; fil < gridSize; fil++)
            for (int col = 0; col < gridSize; col++) {
                int valorCasilla = valores[fil][col];
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

                gridActual.addView(buttonM);
        }
    }



    //Crea las casillas en el GridLayout y la logica de cada una
    public void startGame(int size, int mines, int dp) {
        gridSize = size;
        numbombas = mines;
        finalnumBombas = numbombas;

        selectSize = size;
        selectMine = mines;
        selectDP = dp;

        gridMinas.setRowCount(gridSize);
        gridMinas.setColumnCount(gridSize);

        bombAleatoria(gridSize, numbombas); //Llamamos a la funcion para crear las bombas
        searchMines(gridSize); //Lamamos a la funcion para dar la logica del juego

        SparseArray<Integer> clickCounter = new SparseArray<>();

        for (int fil = 0; fil < gridSize; fil++)
            for (int col = 0; col < gridSize; col++) {
                int IDunica = fil * gridSize + col;
                int color = Color.parseColor("#00FFFFFF");
                int valorCasilla = valores[fil][col];
                int i = fil, j = col;

                ImageButton buttonM = new ImageButton(this);
                buttonM.setId(IDunica);
                buttonM.setImageResource(R.drawable.cuadro_inicial);
                buttonM.setScaleType(ImageView.ScaleType.FIT_XY);
                buttonM.setBackgroundTintList(ColorStateList.valueOf(color));
                buttonM.setPadding(0, 0, 0, 0);
                buttonM.setContentDescription("Diseño Boton");

                int sizeInDp = dp;
                float scale = getResources().getDisplayMetrics().density;
                int sizeInPixels = (int) (sizeInDp * scale);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = sizeInPixels;
                params.height = sizeInPixels;
                buttonM.setLayoutParams(params);

                gridMinas.addView(buttonM);

                //Mostrar por pantalla el numero de bombas en el juego
                numBomb.setText(String.valueOf(finalnumBombas));

                //Accion para mostrar contenido de las casillas
                buttonM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (valorCasilla) {
                            case -1:
                                buttonM.setImageResource(R.drawable.bomba);
                                face.setImageResource(R.drawable.death_face);
                                stopContador();
                                restartGame();
                                Toast.makeText(getApplicationContext(),
                                        "Has explotado una mina.", Toast.LENGTH_LONG).show();
                                gameOver(gridResultado ,gridSize, dp);
                                break;
                            case 0:
                                buttonM.setImageResource(0);
                                expandirEmptySpace(gridMinas, gridSize, i, j);
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

                //Accion para marcar una bomba
                final int botonID = IDunica;
                clickCounter.put(botonID, 0);

                buttonM.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int clickD = clickCounter.get(botonID);
                        face.setImageResource(R.drawable.face_shocked);
                        clickD++;

                        if (clickD > 3)
                            clickD = 1;
                        clickCounter.put(botonID, clickD);

                        switch (clickD){
                            case 1:
                                if (finalnumBombas > 0) {
                                    finalnumBombas--;
                                    numBomb.setText(String.valueOf(finalnumBombas));
                                    buttonM.setImageResource(R.drawable.bandera_mina);
                                    face.setImageResource(R.drawable.smile_face);
                                } else {
                                    Toast.makeText(getApplicationContext(), "No hay mas banderas", Toast.LENGTH_LONG).show();
                                    return true;
                                }
                                return true;
                            case 2:
                                buttonM.setImageResource(R.drawable.cuadro_pregunta);
                                face.setImageResource(R.drawable.smile_face);
                                return true;
                            case 3:
                                if (finalnumBombas < numbombas) {
                                    finalnumBombas++;
                                    numBomb.setText(String.valueOf(finalnumBombas));
                                    buttonM.setImageResource(R.drawable.cuadro_inicial);
                                    face.setImageResource(R.drawable.smile_face);
                                }
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
    }



    //Funcion para reiniciar el juego y eliminar los items del Grid
    public void restartGame() {
        gridMinas.removeAllViews();
        gridResultado.removeAllViews();
    }



    //Funcion para expandir las casillas si es cero
    public void expandirEmptySpace(GridLayout gridMinas, int size, int fil, int col) {
        if (fil < 0 || fil >= gridSize || col < 0 || col >= gridSize)
            return;

        ImageButton botonActual = (ImageButton) gridMinas.getChildAt(fil * size + col);

        if (!botonActual.isEnabled())
            return;

        int valor = valores[fil][col];

        if (valor == 0) {
            botonActual.setImageResource(0);
            botonActual.setEnabled(false);

            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++) {
                    int filaActual = fil + i;
                    int columnaActual = col + j;

                    if (filaActual >= 0 && filaActual < size && columnaActual >= 0 && columnaActual < size)
                        expandirEmptySpace(gridMinas, size, filaActual, columnaActual);
                }

        } else if (valor > 0 && valor < 9) {
            botonActual.setEnabled(false);
            int drawableResource = obtenerDrawable(valor);
            botonActual.setImageResource(drawableResource);
        }
    }



    //Asignar la imagen en base al valor
    public int obtenerDrawable(int valor) {
        switch (valor) {
            case 1:
                return R.drawable.numero1;
            case 2:
                return R.drawable.numero2;
            case 3:
                return R.drawable.numero3;
            case 4:
                return R.drawable.numero4;
            case 5:
                return R.drawable.numero5;
            case 6:
                return R.drawable.numero6;
            case 7:
                return R.drawable.numero7;
            case 8:
                return R.drawable.numero8;
            default:
                return 0;
        }
    }



    //Recoge los parametros del juego
    public void restarParameters() {
        if (selectSize != 0 && selectMine != 0 && selectDP != 0) {
            startGame(selectSize, selectMine, selectDP);
            startContador();
        }
        else
            Toast.makeText(getApplicationContext(), "No se han seleccionado parámetros para reiniciar el juego.", Toast.LENGTH_SHORT).show();
    }



    //Recoge los parametros para mostrarlo al rendirse
    public void surrenderParameters() {
        if (selectSize != 0 && selectDP != 0) {
            intent = new Intent(this, MainActivity2.class);
            Bundle bundle = new Bundle();

            int[] datosGuardados = new int[selectSize * selectSize];
            int num = 0;
            for (int i = 0; i < selectSize; i++)
                for(int j = 0; j < selectSize; j++) {
                    datosGuardados[num] = valores[i][j];
                    num++;
                }

            int dpImage = selectDP;
            intent.putExtra("dpImage", dpImage);

            bundle.putIntArray("datos", datosGuardados);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "No se han seleccionado parámetros para rendirse.", Toast.LENGTH_SHORT).show();
    }



    //Dialogo para Reiniciar el Juego
    public AlertDialog crearDialogoReinicio() {
        builderReinicio = new AlertDialog.Builder(this);

        View dialogView = getLayoutInflater().inflate(R.layout.dialogreset, null);
        builderReinicio.setView(dialogView)
                .setCancelable(true);

        Button bYes = dialogView.findViewById(R.id.btnYes);
        Button bNo = dialogView.findViewById(R.id.btnNo);

        bYes.setOnClickListener(v -> {
            restartGame();
            face.setImageResource(R.drawable.smile_face);
            restarParameters();
            alertReinicio.dismiss();
        });

        bNo.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Has seleccionado NO.", Toast.LENGTH_SHORT).show();
            alertReinicio.dismiss();
        });

        return builderReinicio.create();
    }



    //Dialogo para Rendirse
    public AlertDialog crearDialogoRendirse() {
        builderRendirse = new AlertDialog.Builder(this);
        View dialogV = getLayoutInflater().inflate(R.layout.dialogrendirse, null);
        builderRendirse.setView(dialogV)
                .setCancelable(true);

        Button yes = dialogV.findViewById(R.id.btYes);
        Button no = dialogV.findViewById(R.id.btNo);

        yes.setOnClickListener(v -> {
            restartGame();
            surrenderParameters();
            alertRendirse.dismiss();
        });

        no.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Animo! No te rindas.", Toast.LENGTH_SHORT).show();
            alertRendirse.dismiss();
        });

        return builderRendirse.create();
    }



    //Crear un menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menugrande, menu);
        return true;
    }



    //Seleccion de opciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.instruction) {
            if (button.isChecked())
                button.setChecked(false);
            alertIns.show();
            return true;
        } else if (id == R.id.salida) {
            finish();
            return true;
        } else
            return false;
    }



    //Dialogo para las instrucciones
    public AlertDialog crearDialogoInstructions() {
        builderIns = new AlertDialog.Builder(this);
        View viewIns = getLayoutInflater().inflate(R.layout.dialoginstruction, null);
        button = viewIns.findViewById(R.id.instrDone);

        builderIns.setView(viewIns)
                .setCancelable(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertIns.dismiss();
            }
        });

        return builderIns.create();
    }

}