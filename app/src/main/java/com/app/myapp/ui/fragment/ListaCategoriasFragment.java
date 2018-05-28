package com.app.myapp.ui.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.adapter.CategoryImageAdapter;
import com.app.myapp.model.ImagemCategoria;
import com.app.myapp.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCategoriasFragment extends Fragment {


    public ListaCategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_categorias, container, false);

        TypedArray imagensCategorias = getResources().obtainTypedArray(R.array.imagens_categorias);
        String[] nomesCategorias = getResources().getStringArray(R.array.nomes_categorias);
        List<ImagemCategoria> list = new ArrayList<ImagemCategoria>();

        for (int i = 0; i < nomesCategorias.length; i++) {
            ImagemCategoria imagemCategoria = new ImagemCategoria();

            imagemCategoria.setNome(nomesCategorias[i]);
            imagemCategoria.setImagem(imagensCategorias.getDrawable(i));

            list.add(imagemCategoria);
        }

        ListView listView = (ListView) view.findViewById(R.id.lista_categorias);
        listView.setAdapter(new CategoryImageAdapter(view.getContext(), list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.lista_categorias);
                intent.putExtra(Constant.FILTER.COUNTRY, ((TextView) view.findViewById(R.id.grid_item_label)).getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }

}
