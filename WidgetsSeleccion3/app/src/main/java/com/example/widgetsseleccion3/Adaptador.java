package com.example.widgetsseleccion3;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    public Adaptador (Context context, int layout, ArrayList<String> nombre, int[] imagenes) {
        this.context = context;
        this.layout = layout;
        this.nombre = nombre;
        this.imagenes = imagenes;
        this.opcionMultiple = new SparseBooleanArray(nombre.size());
    }

    private Context context;
    private int layout;
    private ArrayList<String> nombre;
    private int[] imagenes;
    private SparseBooleanArray opcionMultiple;

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

    public void toggleSelection(int position) {
        boolean currentSelected = opcionMultiple.get(position, false);
        opcionMultiple.put(position, !currentSelected);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.spinnerproductos, null);

        String currentName = nombre.get(position);
        int currentImage = imagenes[position];

        TextView textView = v.findViewById(R.id.textoProducto);
        textView.setText(currentName);

        ImageView imageView = v.findViewById(R.id.ImagenP);
        imageView.setImageResource(currentImage);

        if (opcionMultiple.get(position, false))
            textView.setTextColor(Color.BLUE);
        else
            textView.setTextColor(Color.BLACK);

        return v;
    }

    public ArrayList<String> getOpciones() {
        ArrayList<String> opcionesSelect = new ArrayList<>();

        for (int i = 0; i < opcionMultiple.size(); i++) {
            if (opcionMultiple.valueAt(i)) {
                int position = opcionMultiple.keyAt(i);
                opcionesSelect.add(nombre.get(position));
            }
        }

        return opcionesSelect;
    }

}
