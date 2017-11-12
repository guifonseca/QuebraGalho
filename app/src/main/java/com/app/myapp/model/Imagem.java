package com.app.myapp.model;

import java.util.Date;

public class Imagem 
{
	private int    id;
	private Date   dataCriacao;
	private String nome;
	private byte[] bytesImagem;
	
	public Imagem( ){ }
	
	public int getId( ) 
	{
		return id;
	}
	
	public void setId( int id ) 
	{
		this.id = id;
	}
	
	public Date getDataCriacao( ) 
	{
		return dataCriacao;
	}
	
	public void setDataCriacao( Date dataCriacao ) 
	{
		this.dataCriacao = dataCriacao;
	}
	
	public String getNome( )
	{
		return nome;
	}
	
	public void setNome( String nome )
	{
		this.nome = nome;
	}
	
	public byte[] getBytesImagem( ) 
	{
		return bytesImagem;
	}

	public void setBytesImagem( byte[] bytesImage ) 
	{
		this.bytesImagem = bytesImage;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + ", dataCriacao=" + dataCriacao + ", nome=" + nome + "]";
	}
}
