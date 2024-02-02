package com.example.ejemplo1videojuego;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageRana, imageKaori, imageShadow;
    AnimatorSet animatorSet;
    ObjectAnimator translation, transparent, scale, rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textRandom);
        imageShadow = findViewById(R.id.shadow);
        imageRana = findViewById(R.id.ranaRiendo);
        imageKaori = findViewById(R.id.kaoriMiyazono);


        animatorSet = new AnimatorSet();

        translation = ObjectAnimator.ofFloat(textView, "translationY", -40, 10);
        translation.setDuration(4000)
                .setRepeatMode(ValueAnimator.REVERSE);
        translation.setRepeatCount(ValueAnimator.INFINITE);

        transparent = ObjectAnimator.ofFloat(imageShadow, "alpha", 0f, 1f);
        transparent.setDuration(4500)
                .setRepeatMode(ValueAnimator.REVERSE);
        transparent.setRepeatCount(ValueAnimator.INFINITE);

        scale = ObjectAnimator.ofFloat(imageShadow, "scaleX", 2f);
        scale.setDuration(4000)
                .setRepeatMode(ValueAnimator.REVERSE);
        scale.setRepeatCount(ValueAnimator.INFINITE);

        rotation = ObjectAnimator.ofFloat(textView, "rotationY", 120);
        rotation.setDuration(4500)
                .setRepeatMode(ValueAnimator.REVERSE);
        rotation.setRepeatCount(ValueAnimator.INFINITE);



        imageKaori.setImageResource(R.drawable.kaori_miyazono);
        AnimationDrawable animationKaori = (AnimationDrawable) imageKaori.getDrawable();
        animationKaori.start();

    }

    public void startRana(View view) {
        animatorSet.play(translation).with(transparent).with(scale).with(rotation);
        animatorSet.start();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rana_riendo);
        imageRana.startAnimation(animation);
    }

    public void goCanvas(View view) {
        Intent intent = new Intent(this, LienzoCanva.class);
        startActivity(intent);
    }
}