package com.app.myapp.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.myapp.R;
import com.app.myapp.adapter.ListaServicosAdapter;
import com.app.myapp.listener.ListaServicosOnScrollListener;
import com.app.myapp.model.Servico;
import com.app.myapp.model.Servicos;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ServicosTask extends AsyncTask<Void, Void, Servicos> {
    private RelativeLayout load;
    private Context context;
    private View view;
    private static int current_page = 1;
    private int ival = 1;
    private int loadLimit = 10;
    private Servicos servicos = null;
    private List<Servico> listaServicos = new ArrayList<Servico>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    public ServicosTask(View view) {
        this.context = view.getContext();
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        load = (RelativeLayout) view.findViewById(R.id.loadingbar);
        load.setVisibility(RelativeLayout.VISIBLE);
    }

    @Override
    protected Servicos doInBackground(Void... voids) {
        Servicos servicos = null;

        try {
            final String url = "http://192.168.25.28:8080/QueroUmTrampoWebService/rest/servicos";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            servicos = restTemplate.getForObject(url, Servicos.class);

            if (servicos != null && !servicos.getServicos().isEmpty()) {
                loadData();
                setupRecycler();
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return servicos;
    }

    @Override
    protected void onPostExecute(Servicos servicos) {
        load.setVisibility(RelativeLayout.GONE);
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
