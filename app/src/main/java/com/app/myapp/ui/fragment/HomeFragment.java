package com.app.myapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.rest.ServicosTask;
import com.app.myapp.ui.activity.FilterActivity;
import com.app.myapp.ui.activity.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btRegiao = (Button) view.findViewById(R.id.btRegiao);
        btRegiao.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FilterActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.btRegiao);
                startActivity(intent);
            }
        });

        Button btCategorias = (Button) view.findViewById(R.id.btCategorias);
        btCategorias.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FilterActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.btCategorias);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.fab);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ServicosTask servicosTask = new ServicosTask(view);

        servicosTask.execute();
    }
}
