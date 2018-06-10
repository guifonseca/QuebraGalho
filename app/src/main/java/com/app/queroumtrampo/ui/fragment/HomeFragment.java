package com.app.queroumtrampo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.queroumtrampo.Constant;
import com.app.queroumtrampo.R;
import com.app.queroumtrampo.rest.ServicosTask;
import com.app.queroumtrampo.ui.activity.CriarTrampoActivity;
import com.app.queroumtrampo.ui.activity.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    public HomeFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivityCallback.setScreenTitle("Página Inicial");

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CriarTrampoActivity.class));
            }
        });

        ServicosTask servicosTask = new ServicosTask(view);
        servicosTask.execute();
    }

    @Override
    protected int getViewID() {
        return R.layout.fragment_home;
    }
}
