package com.example.fotosyminiatura;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final int capturaImg = 1;
    ImageButton imageResult;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageResult = findViewById(R.id.imageFinal);

    }

    //Abrir la camapara para tomar la foto
    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, capturaImg);
    }

    //Mostrar ultima foto tomada
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == capturaImg && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageUri = saveImageToExternalStorage(imageBitmap);

            imageResult.setImageBitmap(imageBitmap);

            Toast.makeText(this, "Foto tomada con éxito", Toast.LENGTH_SHORT).show();
        }
    }


    //Llevar a la galeria desde la foto
    public void openGallery(View view) {
        if (imageUri != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(imageUri, "image/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Primero toma una foto", Toast.LENGTH_SHORT).show();
        }
    }

    //Guardar la imagen en memoria externa y devolver el Uri
    private Uri saveImageToExternalStorage(Bitmap bitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File miArchivo = new File(root + "/FotosYMiniatura");
        miArchivo.mkdir();

        String fileName = "photo_" + System.currentTimeMillis() + ".jpg";
        File file = new File(miArchivo, fileName);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}