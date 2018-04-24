package com.app.myapp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.myapp.R;

public class ListaServicosHolder extends RecyclerView.ViewHolder {

    public TextView titulo;
    public TextView valor;
    public TextView dataCriacao;

    public ListaServicosHolder(View itemView) {
        super(itemView);
        titulo      = (TextView) itemView.findViewById(R.id.lbTitulo);
        valor       = (TextView) itemView.findViewById(R.id.lbValor);
        dataCriacao = (TextView) itemView.findViewById(R.id.lbDataCriacao);
    }
}
