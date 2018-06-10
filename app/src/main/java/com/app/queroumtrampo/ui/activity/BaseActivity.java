package com.app.queroumtrampo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.queroumtrampo.R;
import com.app.queroumtrampo.callbacks.IActivityCallback;

public abstract class BaseActivity extends AppCompatActivity implements IActivityCallback {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar)
            onSetupActionBar();
    }

    private void onSetupActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void startFragment(int fragmentID, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(fragmentID);

        if (null == f) {
            fm.beginTransaction()
                    .add(fragmentID, fragment)
                    .commitNow();
        } else {
            fm.beginTransaction()
                    .replace(fragmentID, fragment)
                    .commitAllowingStateLoss();
        }
    }

    public void setScreenTitle(String title) {
        if (null != title) {
            if (null != mToolbar) {
                mToolbar.setTitle(title);
            } else if (null != getSupportActionBar()) {
                getSupportActionBar().setTitle(title);
            }
        }
    }

    public void setScreenSubtitle(String title) {
        if (null != title) {
            if (null != mToolbar) {
                mToolbar.setSubtitle(title);
            } else if (null != getSupportActionBar()) {
                getSupportActionBar().setSubtitle(title);
            }
        }
    }

    public abstract int getLayoutID();
}
