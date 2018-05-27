package com.app.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.myapp.Constant;
import com.app.myapp.R;
import com.app.myapp.ui.fragment.CriarServicoFragment;

public class RootActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onSetupNavigation(getIntent().getIntExtra(Constant.EXTRA.CONTENT, 0));
    }

    private void onSetupNavigation(int itemID){
        switch (itemID) {
            case R.id.fab:
                startFragment(R.id.home_fragment, new CriarServicoFragment());
                break;
            default:
                break;
        }
    }
}
