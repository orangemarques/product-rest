package br.com.clientrest.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.clientrest.business.IProdutoService;
import br.com.clientrest.exception.ServiceException;
import br.com.clientrest.model.Produto;
import br.com.clientrest.model.filter.ProdutoFiltro;
import br.com.clientrest.utils.FacesUtils;
/**
 * Classe de controller de família de produto
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 03/07/2015 11:55:41
 */
@Controller("produtoController")
@Scope("session")
public class ProdutoController implements Serializable {
	
	private static final long serialVersionUID = -6670415022724520311L;

	private Logger log = Logger.getLogger(this.getClass());
	private Produto produto;
	private List<Produto> produtos;
	private int totalEncontrado;
	private String nome;
		
	@Resource
	private IProdutoService produtoService;
	
	public String initAction() {
		return "list-produto";
	}
	
	public void initActionListener(ActionEvent event) {
		this.clearFieldSearchActionListener(event);
		produtos = new ArrayList<Produto>();
		produto = new Produto();
		totalEncontrado = 0;
	}

	public void listActionListener(ActionEvent event) {
		try {
			if ((nome == null) || (nome.trim().length() > 0)) {
				produto = produtoService.getProduto(new ProdutoFiltro(null, ((nome != null) && (nome.trim().length() > 0)) ? nome : null, Boolean.TRUE));
				produtos = new ArrayList<Produto>();
				produtos.add(produto);
			}
			else
				produtos = produtoService.getAll(new ProdutoFiltro(null, null, Boolean.TRUE));
			totalEncontrado = produtos.size();
		}
		catch(ServiceException error) {
			FacesUtils.addErrorMessage("produto", FacesUtils.getMessageByKey(error.getMessage()));
		}
		catch(Exception error) {
			log.error("Ocorreu um erro ao listar produtos. ",error);
			FacesUtils.addErrorMessage("produto", FacesUtils.getMessageByKey("client_rest_erro_produto_list"));
		}
	}
	
	public void clearFieldSearchActionListener(ActionEvent event) {
		this.nome= "";
	}

	public int getTotalEncontrado() {
		return totalEncontrado;
	}

	public void setTotalEncontrado(int totalEncontrado) {
		this.totalEncontrado = totalEncontrado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
		
}
