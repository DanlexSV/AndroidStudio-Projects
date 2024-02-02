package com.example.grabarvideo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button startB;
    VideoView miVideo;
    private static final int TakeVideo = 1;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startB = findViewById(R.id.grabar);
        miVideo = findViewById(R.id.videoFinal);
    }

    //Abrir la camara de Video
    public void startVideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, TakeVideo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TakeVideo && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            miVideo.setMediaController(new MediaController(this));
            miVideo.setVideoURI(uri);
            miVideo.start();

            videoUri = saveVideo(uri);

            Toast.makeText(this, "Video guardado con éxito", Toast.LENGTH_SHORT).show();
        }


    }

    private Uri saveVideo(Uri videoUri) {
        try {
            AssetFileDescriptor assetFile = getContentResolver().openAssetFileDescriptor(videoUri, "r");
            FileInputStream inputStream = assetFile.createInputStream();
            FileOutputStream outputStream = openFileOutput(FileName(), Context.MODE_PRIVATE);

            byte[] bytes = new byte[1024];
            int len;

            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
            return Uri.fromFile(getFileStreamPath(FileName()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String FileName() {
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fechaFinal = fecha + ".mp4";

        return fechaFinal;
    }
}