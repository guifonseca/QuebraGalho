package com.app.myapp.model;

import java.io.Serializable;
import java.util.Date;

public class Usuario {

	private int id;
	private String nome;
	private String apelido;
	private int tipoCadastro;
	private double cpfCnpj;
	private int sexo;
	private String email;
	private String senha;
	private String celular;
	private String cep;
	private String endereco;
	private int numero;
	private String complemento;
	private String estado;
	private String regiao;
	private Date dataCriacao;
	private Imagem imagem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public int getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(int tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	public double getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(double cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", apelido=" + apelido + ", tipoCadastro=" + tipoCadastro
				+ ", cpfCnpj=" + cpfCnpj + ", sexo=" + sexo + ", email=" + email + ", senha=" + senha + ", celular="
				+ celular + ", cep=" + cep + ", endereco=" + endereco + ", numero=" + numero + ", complemento="
				+ complemento + ", estado=" + estado + ", regiao=" + regiao + ", dataCriacao=" + dataCriacao
				+ ", imagem=" + imagem + "]";
	}
}
