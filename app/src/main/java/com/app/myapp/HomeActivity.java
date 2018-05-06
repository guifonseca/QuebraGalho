package com.app.myapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.app.myapp.adapter.ListaServicosAdapter;
import com.app.myapp.listener.ListaServicosOnScrollListener;
import com.app.myapp.model.Servico;
import com.app.myapp.model.Servicos;
import com.app.myapp.rest.ServicosTask;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabHost tabHost;
    private static int current_page = 1;
    private int ival = 1;
    private int loadLimit = 10;
    private Servicos servicos = null;
    private List<Servico> listaServicos = new ArrayList<Servico>();
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(view.getContext(), CriarServicoActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.active_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ServicosTask servicosTask = new ServicosTask();

        try {
            servicos = servicosTask.execute().get();
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("teste").setTitle("titulo");
            AlertDialog dialog = builder.create();
        }

        if (servicos == null) {
            Toast toast = Toast.makeText(this, "Hello toast!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            loadData();
            setupRecycler();
        }

        Button btRegiao = (Button) findViewById(R.id.btRegiao);

        btRegiao.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), ListaRegiaoActivity.class), 0);
            }
        });

        Button btCategorias = (Button) findViewById(R.id.btCategorias);

        btCategorias.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), ListaCategoriasActivity.class), 0);
            }
        });
    }

    private void setupRecycler() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_layour_recycler);

        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.active_home);

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_servicos:
                // Handle the camera action
                break;

            case R.id.nav_inserir_servicos:
                break;

            case R.id.nav_chat:
                break;

            case R.id.nav_fale_conosco:
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_send:
                break;

            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.active_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
