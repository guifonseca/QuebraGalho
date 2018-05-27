package com.app.myapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.adapter.ListaServicosAdapter;
import com.app.myapp.listener.ListaServicosOnScrollListener;
import com.app.myapp.model.Servico;
import com.app.myapp.model.Servicos;
import com.app.myapp.rest.ServicosTask;
import com.app.myapp.ui.activity.ListaCategoriasActivity;
import com.app.myapp.ui.activity.ListaRegiaoActivity;
import com.app.myapp.ui.activity.RootActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static int current_page = 1;
    private int ival = 1;
    private int loadLimit = 10;
    private Servicos servicos = null;
    private List<Servico> listaServicos = new ArrayList<Servico>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;
    private View view;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        ServicosTask servicosTask = new ServicosTask();

        try {
            servicos = servicosTask.execute().get();
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("teste").setTitle("titulo");
            AlertDialog dialog = builder.create();
        }

        if (servicos == null) {
            Toast toast = Toast.makeText(view.getContext(), "Hello toast!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            loadData();
            setupRecycler();
        }

        Button btRegiao = (Button) view.findViewById(R.id.btRegiao);

        btRegiao.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), ListaRegiaoActivity.class), 0);
            }
        });

        Button btCategorias = (Button) view.findViewById(R.id.btCategorias);

        btCategorias.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), ListaCategoriasActivity.class), 0);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(view.getContext(), RootActivity.class);
                intent.putExtra(Constant.EXTRA.CONTENT, R.id.fab);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupRecycler() {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_layour_recycler);

        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListaServicosAdapter(listaServicos);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new ListaServicosOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        });
    }

    private void loadData() {

        for (int i = ival; i <= loadLimit && i <= servicos.getServicos().size(); i++) {
            Servico servico = servicos.getServicos().get(i - 1);

            listaServicos.add(servico);
            ival++;
        }
    }

    private void loadMoreData() {

        loadLimit = ival + 10;

        for (int i = ival; i <= loadLimit && i <= servicos.getServicos().size(); i++) {
            Servico servico = servicos.getServicos().get(i - 1);

            listaServicos.add(servico);
            ival++;
        }

        //mAdapter.notifyDataSetChanged();
    }

}
