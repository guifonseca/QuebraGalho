package com.app.queroumtrampo.ui.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.queroumtrampo.Constant;
import com.app.queroumtrampo.R;
import com.app.queroumtrampo.ui.adapter.CategoryImageAdapter;
import com.app.queroumtrampo.model.ImagemCategoria;
import com.app.queroumtrampo.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCategoriasFragment extends BaseFragment {


    public ListaCategoriasFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivityCallback.setScreenTitle("Categorias");
        onListViewListener(view);
    }

    @Override
    protected int getViewID() {
        return R.layout.fragment_lista_categorias;
    }

    private void onListViewListener(View view) {
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
    }
}
