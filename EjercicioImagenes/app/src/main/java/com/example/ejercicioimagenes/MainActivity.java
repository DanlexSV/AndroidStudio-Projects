package com.example.ejercicioimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button bResolution;
    ImageView ImageValo;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bResolution = findViewById(R.id.BResolution);
        ImageValo = findViewById(R.id.ValoImage);

        ImageValo.setImageResource(R.drawable.image_valorant);

        bResolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i % 2 == 0)
                    ImageValo.setVisibility(View.VISIBLE);
                else
                    ImageValo.setVisibility(View.INVISIBLE);
            }
        });
    }
}