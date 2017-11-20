package com.app.myapp.validate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Guilherme on 19/11/2017.
 */

public class ValidaCadastroUsuario extends DialogFragment {
    private String mensagem;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(mensagem).setTitle("Aviso");

        return builder.create();
    }

    public boolean validaEmailSenha( String email, String senha ) {
        boolean temErro = email.equals("") || senha.equals("");

        if( temErro )
            mensagem = "Email ou senha não foram preenchidos";

        return temErro;
    }
}
