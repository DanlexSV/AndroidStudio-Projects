package com.example.videostreaming;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView miVideo;
    Uri uri;
    Button rebx2, rebx4, rebx8;
    Handler handler;
    int posicionActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miVideo = findViewById(R.id.VideoMIX);
        rebx2 = findViewById(R.id.butx2);
        rebx4 = findViewById(R.id.butx4);
        rebx8 = findViewById(R.id.butx8);

        handler = new Handler();

        String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        uri = Uri.parse(url);

        miVideo.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(miVideo);
        miVideo.setMediaController(mediaController);

        miVideo.start();

        rebx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewindX2();
            }
        });

        rebx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewindX4();
            }
        });

        rebx8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewindX8();
            }
        });
    }

    private void rewindX2() {
        miVideo.pause();

        posicionActual = miVideo.getCurrentPosition();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                miVideo.seekTo(posicionActual - 2000);
                miVideo.start();
            }
        }, 100);
    }

    private void rewindX4() {
        miVideo.pause();

        posicionActual = miVideo.getCurrentPosition();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                miVideo.seekTo(posicionActual - 4000);
                miVideo.start();
            }
        }, 100);
    }

    private void rewindX8() {
        miVideo.pause();

        posicionActual = miVideo.getCurrentPosition();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                miVideo.seekTo(posicionActual - 8000);
                miVideo.start();
            }
        }, 100);
    }
}