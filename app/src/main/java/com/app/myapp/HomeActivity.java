package com.app.myapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toast;

import com.app.myapp.model.Categorias;
import com.app.myapp.rest.CategoriasTask;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout          drawer = (DrawerLayout)findViewById( R.id.active_home );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.setDrawerListener( toggle );
        toggle.syncState( );

        NavigationView navigationView = (NavigationView)findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        CategoriasTask downloadCategorias = new CategoriasTask( );
        Categorias         categorias         = null;

        try {
            categorias = downloadCategorias.execute( ).get( );
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("teste").setTitle("titulo");
            AlertDialog dialog = builder.create();
        }

        if( categorias == null )
        {
            Toast toast = Toast.makeText(this, "Hello toast!", Toast.LENGTH_LONG);
            toast.show();
        }else {
            GridView gridView = (GridView) findViewById(R.id.gridCategorias);
            gridView.setAdapter(new CategoryImageAdapter(this, categorias));
        }

        createTabHost();
    }

    private void createTabHost( )
    {
        TabHost host = (TabHost)findViewById( R.id.tabHome );
        host.setup( );

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec( "Região" );
        spec.setContent  ( R.id.tab1 );
        spec.setIndicator( "Região"  );
        host.addTab      ( spec      );

        //Tab 2
        spec = host.newTabSpec( "Categoria" );
        spec.setContent  ( R.id.tab2    );
        spec.setIndicator( "Categoria"  );
        host.addTab      ( spec         );

        //Tab 3
        spec = host.newTabSpec( "Filtro" );
        spec.setContent  ( R.id.tab3 );
        spec.setIndicator( "Filtro"  );
        host.addTab      ( spec      );

        host.setCurrentTab( 1 );
    }

    @Override
    public void onBackPressed( )
    {
        DrawerLayout drawer = (DrawerLayout)findViewById( R.id.active_home );

        if( drawer.isDrawerOpen( GravityCompat.START ) )
            drawer.closeDrawer( GravityCompat.START );
        else
            super.onBackPressed( );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId( );

        if( id == R.id.action_settings )
            return true;

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected( MenuItem item )
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId( );

        switch( id )
        {
            case R.id.nav_servicos:
                // Handle the camera action
                break;

            case R.id.nav_inserir_servicos:
                break;

            case  R.id.nav_chat:
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
