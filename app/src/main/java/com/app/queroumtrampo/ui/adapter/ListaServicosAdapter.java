package com.app.queroumtrampo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.queroumtrampo.R;
import com.app.queroumtrampo.ui.holder.ListaServicosHolder;
import com.app.queroumtrampo.model.Servico;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListaServicosAdapter extends RecyclerView.Adapter<ListaServicosHolder> {

    private final List<Servico> servicos;

    public ListaServicosAdapter(List<Servico> servicos) {
        this.servicos = servicos;
    }

    @Override
    public ListaServicosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_servicos, null);

        ListaServicosHolder viewHolder = new ListaServicosHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListaServicosHolder holder, int position) {
        holder.titulo.setText(servicos.get(position).getTitulo());
        holder.valor.setText("R$" + servicos.get(position).getValor().toString());
        holder.dataCriacao.setText(new SimpleDateFormat("dd MMM HH:mm", new Locale("PT", "BR")).format(servicos.get(position).getDataCriacao()));
    }

    @Override
    public int getItemCount() {
        return servicos != null ? servicos.size() : 0;
    }
}
