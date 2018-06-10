package com.app.queroumtrampo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.queroumtrampo.R;

public class ListaRegiaoAdapter extends BaseAdapter {

    private String[] estados;
    private Context context;
    private View gridView;

    public ListaRegiaoAdapter(Context context, String[] estados){
        this.context = context;
        this.estados = estados;
    }

    @Override
    public int getCount() {
        return estados.length;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.lista_categorias, null);

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(estados[position]);

        } else
            gridView = (View) convertView;

        return gridView;
    }
}
