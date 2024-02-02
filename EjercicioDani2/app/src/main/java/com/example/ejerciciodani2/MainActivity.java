package com.example.ejerciciodani2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    TextView textView;
    ArrayList<String> arrayChar;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listaCaracteristicas);
        button = findViewById(R.id.BAnalizar);
        textView = findViewById(R.id.textFinal);

        arrayChar = new ArrayList<String>();
        arrayChar.add("Ataque de lejos");
        arrayChar.add("Ataque de cerca");
        arrayChar.add("Ataque consecutivo");
        arrayChar.add("Personaje de apoyo");
        arrayChar.add("Chica");
        arrayChar.add("Chico");
        arrayChar.add("Ulti variada");

        adapter = new ArrayAdapter<String>(this, R.layout.lista, arrayChar);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray selected = listView.getCheckedItemPositions();

                ArrayList<String> caracterSelect = new ArrayList<String>();

                for (int i = 0; i < selected.size(); i++) {
                    int position = selected.keyAt(i);
                    if (selected.get(position)) {
                        String caracteristicas = arrayChar.get(position);
                        caracterSelect.add(caracteristicas);
                    }
                }

                String textSelect = TextUtils.join(", ", caracterSelect);
                textView.setText(textSelect);
            }
        });
    }
}