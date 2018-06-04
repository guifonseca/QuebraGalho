package com.app.myapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.ui.activity.HomeActivity;

public class ListaRegiaoFragment extends BaseFragment {
    private ListView mListView;

    public ListaRegiaoFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivityCallback.setScreenTitle("Região");
        onListViewListener(view);
    }

    @Override
    protected int getViewID() {
        return R.layout.fragment_lista_regiao;
    }

    private void onListViewListener(View view) {
        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, estados);

        mListView = (ListView) view.findViewById(R.id.lista_regiao);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.lista_regiao);
                intent.putExtra(Constant.FILTER.COUNTRY, (String) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }
}
