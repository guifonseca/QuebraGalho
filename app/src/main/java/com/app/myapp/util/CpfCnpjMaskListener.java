package com.app.myapp.util;

import android.view.View;
import android.widget.EditText;

/**
 * Created by Guilherme on 19/11/2017.
 */

public class CpfCnpjMaskListener implements View.OnFocusChangeListener {
    private EditText cpfCnpjEditText;

    public CpfCnpjMaskListener(EditText cpfCnpjEditText){
        this.cpfCnpjEditText = cpfCnpjEditText;
    }

    @Override
    public void onFocusChange(View view, boolean focus) {
        if(!focus)
        {

        }
    }
}
