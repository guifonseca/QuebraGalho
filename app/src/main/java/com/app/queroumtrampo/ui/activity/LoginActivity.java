package com.app.queroumtrampo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.queroumtrampo.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        startActivityForResult(intent, 0);
    }

    public void criarConta(View view) {
        Intent intent = new Intent(view.getContext(), CriarContaActivity.class);
        startActivityForResult(intent, 0);
    }
}
