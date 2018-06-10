package com.app.queroumtrampo.model;

import java.io.Serializable;
import java.util.Date;

public class Categoria
{
	private int    id;
	private String nome;
	private Date   dataCriacao;
	private Imagem imagem;
	
	public int getId( ) 
	{
		return id;
	}
	
	public void setId( int id ) 
	{
		this.id = id;
	}
	
	public String getNome( ) 
	{
		return nome;
	}
	
	public void setNome( String nome ) 
	{
		this.nome = nome;
	}
	
	public Date getDataCriacao( ) 
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao ) 
	{
		this.dataCriacao = dataCriacao;
	}

	public Imagem getImagem( ) 
	{
		return imagem;
	}
	
	public void setImagem( Imagem imagem ) 
	{
		this.imagem = imagem;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", dataCriacao=" + dataCriacao + ", imagem=" + imagem + "]";
	}
}
