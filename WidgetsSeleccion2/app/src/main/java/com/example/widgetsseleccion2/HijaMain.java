package com.example.widgetsseleccion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class HijaMain extends BaseAdapter {
    public HijaMain (Context context, int layout, ArrayList<String> nombre, int[] imagenes) {
        this.context = context;
        this.layout = layout;
        this.nombre = nombre;
        this.imagenes = imagenes;
    }
    private Context context;
    private int layout;
    private ArrayList<String> nombre;
    private int[] imagenes;

    @Override
    public int getCount() {
        return nombre.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.listapaises, null);

        String currentName = nombre.get(position);
        int currentImage = imagenes[position];

        TextView textView = v.findViewById(R.id.textoPais);
        textView.setText(currentName);

        ImageView imageView = v.findViewById(R.id.Imagen);
        imageView.setImageResource(currentImage);

        return v;
    }
}
