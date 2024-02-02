package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    public Adaptador(Context context, int layout, ArrayList<String> asunto) {
        this.context = context;
        this.layout = layout;
        this.asunto = asunto;
        this.opcionMultiple = new SparseBooleanArray(asunto.size());
    }

    private Context context;
    private int layout;
    private ArrayList<String> asunto;
    private SparseBooleanArray opcionMultiple;

    @Override
    public int getCount() {
        return asunto.size();
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

        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.spinner, null);

        String currentAsunto= asunto.get(position);
        TextView textView = v.findViewById(R.id.textoAsunto);
        textView.setText(currentAsunto);

        if (opcionMultiple.get(position, false))
            textView.setTextColor(Color.BLUE);
        else
            textView.setTextColor(Color.BLACK);

        return v;
    }

    public ArrayList<String> getAsunto() {
        ArrayList<String> opcionesSelect = new ArrayList<>();

        for (int i = 0; i < opcionMultiple.size(); i++)
            if (opcionMultiple.valueAt(i)) {
                int position = opcionMultiple.keyAt(i);
                opcionesSelect.add(asunto.get(position));
            }

        return opcionesSelect;
    }
}
