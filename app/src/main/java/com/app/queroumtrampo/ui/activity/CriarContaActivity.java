package com.app.queroumtrampo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.app.queroumtrampo.R;
import com.app.queroumtrampo.validate.ValidaCadastroUsuario;

/**
 * Created by Guilherme on 12/11/2017.
 */

public class CriarContaActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState  );
        setContentView( R.layout.activity_criar_conta);
    }

    public void cadastrar(View view)
    {
        TextView txEmail = (TextView)findViewById(R.id.txEmail);
        TextView txSenha = (TextView)findViewById(R.id.txSenha);

        ValidaCadastroUsuario valida = new ValidaCadastroUsuario( );
        boolean temErro = valida.validaEmailSenha(txEmail.getText().toString(), txSenha.getText().toString());

        if(temErro)
            valida.show(getFragmentManager(), "validaCadastroUsuario");
        else
        {
            Intent intent = new Intent( view.getContext( ), EditarContaActivity.class );
            startActivityForResult( intent, 0 );
        }
    }
}
