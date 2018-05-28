package com.app.myapp.model;

import android.graphics.drawable.Drawable;

public class ImagemCategoria {

    private String nome;
    private Drawable imagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Drawable getImagem() {
        return imagem;
    }

    public void setImagem(Drawable imagem) {
        this.imagem = imagem;
    }
}
