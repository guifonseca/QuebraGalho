package com.app.myapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.ui.fragment.HomeFragment;
import com.app.myapp.ui.fragment.ListaCategoriasFragment;
import com.app.myapp.ui.fragment.ListaRegiaoFragment;

public class FilterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

        onSetupNavigation(getIntent().getIntExtra(Constant.EXTRA.CONTENT, 0));
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_home;
    }

    private void onSetupNavigation(int itemID) {
        switch (itemID) {
            case R.id.btCategorias:
                startFragment(R.id.home_fragment, new ListaCategoriasFragment());
                break;
            case R.id.btRegiao:
                startFragment(R.id.home_fragment, new ListaRegiaoFragment());
                break;
            case R.id.lista_regiao:
            case R.id.lista_categorias:
                startFragment(R.id.home_fragment, new HomeFragment());
                break;
            default:
                break;
        }
    }
}
