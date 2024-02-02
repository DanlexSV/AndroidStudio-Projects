package com.example.proyectorestaurante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorTypeFood extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<String> name;

    public AdaptadorTypeFood(Context context, int layout, ArrayList<String> name) {
        this.context = context;
        this.layout = layout;
        this.name = name;
    }

    @Override
    public int getCount() {
        return name.size();
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
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.spinner_add_product, null);

        String currentName = name.get(position);
        TextView textView = view.findViewById(R.id.textoAlergeno);
        textView.setText(currentName);

        return view;
    }
}
