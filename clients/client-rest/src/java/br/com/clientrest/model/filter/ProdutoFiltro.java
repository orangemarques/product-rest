package br.com.clientrest.model.filter;

import java.io.Serializable;

public class ProdutoFiltro implements Serializable {

	private static final long serialVersionUID = 4952896377779788485L;
	
	private Short id;
	private String nome;
	private boolean pesquisaInativo;
	
	/**
	 * M�todo construtor da classe FamiliaProduto
	 * @param id - ID da fam�lia de produto
	 * @param nome - Nome da fam�lia de produto
	 * @param pesquisaInativo - Valor booleano informando se � para incluir na pesquisa fam�lias de produtos inativas.
	 */
	public ProdutoFiltro(Short id, String nome, boolean pesquisaInativo){
		this.id = id;
		this.nome = nome;
		this.pesquisaInativo = pesquisaInativo;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPesquisaInativo() {
		return pesquisaInativo;
	}

	public void setPesquisaInativo(boolean pesquisaInativo) {
		this.pesquisaInativo = pesquisaInativo;
	}
	
}
