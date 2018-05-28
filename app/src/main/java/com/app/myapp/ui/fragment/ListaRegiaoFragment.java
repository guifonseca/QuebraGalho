package com.app.myapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.ui.activity.HomeActivity;

public class ListaRegiaoFragment extends Fragment {

    public ListaRegiaoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_regiao, container, false);

        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, estados);

        ListView listView = (ListView) view.findViewById(R.id.lista_regiao);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.lista_regiao);
                intent.putExtra(Constant.FILTER.COUNTRY, (String) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
        return view;
    }
}
