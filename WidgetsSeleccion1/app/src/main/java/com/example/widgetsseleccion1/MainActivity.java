package com.example.widgetsseleccion1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView Roles;
    LinearLayout LayoutAgentes;
    Spinner Iniciador, Duelista, Centinela, Smoker;
    ArrayList<String> arrayRoles, arrayIniciador, arrayDuelist, arrayCent, arraySmok;
    String[] IniciadorA = new String[]{"Skye", "Breach", "Kayo", "Sova", "Fade", "Geko"};
    String[] DuelistaA = new String[]{"Jett", "Raze", "Reyna", "Phoenix", "Yoru", "Neon"};
    String[] CentinelA = new String[]{"Chamber", "Chyper", "Sage", "Killjoy", "Deadlock"};
    String[] SmokerA = new String[]{"Brimstone", "Omen", "Viper", "Astra", "Harbor"};
    ArrayAdapter<String> adapter, adapterI, adapterD, adapterC, adapterS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Roles = findViewById(R.id.RolesValo);
        Iniciador = findViewById(R.id.Iniciador);
        Duelista = findViewById(R.id.Duelista);
        Centinela = findViewById(R.id.Centinela);
        Smoker = findViewById(R.id.Smoker);
        LayoutAgentes = findViewById(R.id.LayoutAgentes);

        arrayRoles = new ArrayList<String>();
        arrayRoles.add("Iniciador");
        arrayRoles.add("Duelista");
        arrayRoles.add("Centinela");
        arrayRoles.add("Smoker");

        arrayIniciador = new ArrayList<String>();
        arrayIniciador.add("Skye");
        arrayIniciador.add("Breach");
        arrayIniciador.add("Kayo");
        arrayIniciador.add("Sova");
        arrayIniciador.add("Fade");
        arrayIniciador.add("Geko");

        arrayDuelist = new ArrayList<String>();
        arrayDuelist.add("Jett");
        arrayDuelist.add("Raze");
        arrayDuelist.add("Reyna");
        arrayDuelist.add("Phoenix");
        arrayDuelist.add("Yoru");
        arrayDuelist.add("Neon");

        arrayCent = new ArrayList<String>();
        arrayCent.add("Chamber");
        arrayCent.add("Chyper");
        arrayCent.add("Sage");
        arrayCent.add("Killjoy");
        arrayCent.add("Deadlock");

        arraySmok = new ArrayList<String>();
        arraySmok.add("Brimstone");
        arraySmok.add("Omen");
        arraySmok.add("Viper");
        arraySmok.add("Astra");
        arraySmok.add("Harbor");

        adapter = new ArrayAdapter<String>(this, R.layout.textoarray, arrayRoles);
        adapterI = new ArrayAdapter<String>(this, R.layout.textoinic, arrayIniciador);
        adapterD = new ArrayAdapter<String>(this, R.layout.textoduel, arrayDuelist);
        adapterC = new ArrayAdapter<String>(this, R.layout.textocen, arrayCent);
        adapterS = new ArrayAdapter<String>(this, R.layout.textosmoke, arraySmok);

        Roles.setAdapter(adapter);
        Iniciador.setAdapter(adapterI);
        Duelista.setAdapter(adapterD);
        Centinela.setAdapter(adapterC);
        Smoker.setAdapter(adapterS);

        Roles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutAgentes.setVisibility(View.VISIBLE);
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                switch (selectedItem) {
                    case "Iniciador":
                        Iniciador.setVisibility(View.VISIBLE);
                        Duelista.setVisibility(View.INVISIBLE);
                        Centinela.setVisibility(View.INVISIBLE);
                        Smoker.setVisibility(View.INVISIBLE);
                        Iniciador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Log.i("message", IniciadorA[i]);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                        break;
                    case "Duelista":
                        Duelista.setVisibility(View.VISIBLE);
                        Iniciador.setVisibility(View.INVISIBLE);
                        Centinela.setVisibility(View.INVISIBLE);
                        Smoker.setVisibility(View.INVISIBLE);
                        Duelista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Log.i("message", DuelistaA[i]);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                        break;
                    case "Centinela":
                        Centinela.setVisibility(View.VISIBLE);
                        Iniciador.setVisibility(View.INVISIBLE);
                        Duelista.setVisibility(View.INVISIBLE);
                        Smoker.setVisibility(View.INVISIBLE);
                        Centinela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Log.i("message", CentinelA[i]);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                        break;
                    case "Smoker":
                        Smoker.setVisibility(View.VISIBLE);
                        Iniciador.setVisibility(View.INVISIBLE);
                        Duelista.setVisibility(View.INVISIBLE);
                        Centinela.setVisibility(View.INVISIBLE);
                        Smoker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Log.i("message", SmokerA[i]);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                        break;
                }
            }
        });

    }
}