package com.app.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.myapp.R;
import com.app.myapp.ui.fragment.HomeFragment;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startFragment(R.id.home_fragment, new HomeFragment());
    }
}
