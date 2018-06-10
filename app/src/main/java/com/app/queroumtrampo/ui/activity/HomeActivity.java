package com.app.queroumtrampo.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.app.queroumtrampo.R;
import com.app.queroumtrampo.ui.fragment.HomeFragment;
import com.app.queroumtrampo.ui.fragment.ListaCategoriasFragment;
import com.app.queroumtrampo.ui.fragment.ListaRegiaoFragment;

public class HomeActivity extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        startFragment(R.id.home_fragment, new HomeFragment());
        onSetupBottomNavigation();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_home;
    }

    private void onSetupBottomNavigation() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                startFragment(R.id.home_fragment, new HomeFragment());
                                break;
                            case R.id.action_regiao:
                                startFragment(R.id.home_fragment, new ListaRegiaoFragment());
                                break;
                            case R.id.action_categoria:
                                startFragment(R.id.home_fragment, new ListaCategoriasFragment());
                                break;
                            default:
                                break;
                        }

                        return true;
                    }
                }
        );
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
}
