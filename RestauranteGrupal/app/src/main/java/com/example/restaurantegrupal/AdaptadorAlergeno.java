package com.example.restaurantegrupal;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorAlergeno extends BaseAdapter {

    public AdaptadorAlergeno(Context context, int layout, ArrayList<String> name) {
        this.context = context;
        this.layout = layout;
        this.name = name;
        this.opcionMultiple = new SparseBooleanArray(name.size());
    }

    private Context context;
    private int layout;
    private ArrayList<String> name;
    private SparseBooleanArray opcionMultiple;

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
        view = layoutInflater.inflate(R.layout.spinner_alergenos, null);

        String currentName = name.get(position);
        TextView textView = view.findViewById(R.id.textoAlergeno);
        textView.setText(currentName);

        if (opcionMultiple.get(position, false))
            textView.setTextColor(Color.BLUE);
        else
            textView.setTextColor(Color.BLACK);

        return view;
    }

    public void toggleSelection(int position) {
        boolean currentSelected = opcionMultiple.get(position, false);
        opcionMultiple.put(position, !currentSelected);
        notifyDataSetChanged();
    }

    public ArrayList getOptions() {
        ArrayList<String> selectOptions= new ArrayList<>();

        for (int i = 0; i < opcionMultiple.size(); i++)
            if (opcionMultiple.valueAt(i)) {
                int position = opcionMultiple.keyAt(i);
                selectOptions.add(name.get(position));
            }

        return selectOptions;
    }
}
