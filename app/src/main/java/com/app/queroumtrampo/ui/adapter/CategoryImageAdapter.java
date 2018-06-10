package com.app.queroumtrampo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.queroumtrampo.R;
import com.app.queroumtrampo.model.ImagemCategoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 04/02/2017.
 */

public class CategoryImageAdapter extends BaseAdapter {
    private TextView textView;
    private View gridView;
    private Context context;
    private List<ImagemCategoria> list = new ArrayList<ImagemCategoria>();

    public CategoryImageAdapter(Context context, List<ImagemCategoria> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.lista_categorias, null);

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(list.get(position).getNome());

            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setImageDrawable(list.get(position).getImagem());

        } else
            gridView = (View) convertView;

        return gridView;
    }
}
