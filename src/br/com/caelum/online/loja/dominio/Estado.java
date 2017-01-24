package br.com.caelum.online.loja.dominio;

import java.util.ArrayList;
import java.util.List;

public class Estado {

	private Long id;
	private String nome;
	private String Sigla;
	private List<Cidade> cidades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String getSigla() {
		return Sigla;
	}

	public void setSigla(String sigla) {
		Sigla = sigla;
	}

	public void addCidade(Cidade cidade) {
		if(cidades == null) {
			cidades = new ArrayList<Cidade>();
		}
		cidade.setEstado(this);
		cidades.add(cidade);
	}
}
