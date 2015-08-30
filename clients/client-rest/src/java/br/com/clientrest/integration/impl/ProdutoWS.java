package br.com.clientrest.integration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.clientrest.exception.ProdutoInexistenteException;
import br.com.clientrest.exception.RepositoryException;
import br.com.clientrest.integration.IProdutoWS;
import br.com.clientrest.model.Produto;
import br.com.clientrest.model.filter.ProdutoFiltro;

@Component("produtoWS")
public class ProdutoWS implements IProdutoWS {

	private Logger log = Logger.getLogger(this.getClass());
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Transactional(readOnly=true)	
	public List<Produto> getAll(ProdutoFiltro produtoFiltro) throws RepositoryException {
		try {
			String retorno = restTemplate.getForObject("http://192.168.173.1/api/v1/application/products", String.class);
			List<Produto> produtos = new ArrayList<Produto>();
			Produto produto = null;
			Map<String,String> map = new HashMap<String,String>();
			if ((retorno != null) && (retorno.trim().length() > 0)) {
				retorno = retorno.replace("[", "");
				retorno = retorno.replace("]", "");
				retorno = retorno.replace("\"", "");
				retorno = retorno.replace("},{", "&");
				String vetor1[] = retorno.split("&");
				
				for (int i=0; i<vetor1.length;i++) {
					map.clear();;
					String vetor[] = vetor1[i].split(",");
					for (int j=0; j<vetor.length;j++) {
						String vetorAux[] = vetor[j].split(":");
						map.put(vetorAux[0], vetorAux[1]);
					}
					if (!map.isEmpty()) {
						produto = new Produto();
						produto.setId(map.get("_id"));
						produto.setBarcode(map.get("bar_code"));
						produto.setNome(map.get("product"));
						produto.setLoja(map.get("trade"));
						produto.setPreco(map.get("price"));
						produtos.add(produto);
					}
				}
				
			}
			return produtos;
		}
		catch(HibernateException erro) {
			log.error("Erro na consulta getAll ",erro);
			throw new RepositoryException("Exception na chamada do método getAll() da classe "+this.getClass(), erro.getCause()); 
		}
	}

	@Transactional(readOnly=true)
	public Produto getProduto(ProdutoFiltro produtoFiltro) throws ProdutoInexistenteException, RepositoryException {
		try {
			Produto produto = null;
			String retorno = restTemplate.getForObject("http://192.168.173.1/api/v1/application/product/"+produtoFiltro.getNome(), String.class);
			Map<String,String> map = null;
			if ((retorno != null) && (retorno.trim().length() > 0)) {
				retorno = retorno.replace("{", "");
				retorno = retorno.replace("}", "");
				retorno = retorno.replace("\"", "");
				map = new HashMap<String,String>();
				String vetor[] = retorno.split(",");
				for (int i=0; i<vetor.length;i++) {
					String vetorAux[] = vetor[i].split(":");
					map.put(vetorAux[0], vetorAux[1]);
				}
			}
			if (map != null) {
				produto = new Produto();
				produto.setId(map.get("_id"));
				produto.setBarcode(map.get("bar_code"));
				produto.setNome(map.get("product"));
				produto.setLoja(map.get("trade"));
				produto.setPreco(map.get("price"));
			}
			return produto;
		}
		catch(HibernateException error) {
			throw new RepositoryException("Exception na chamada do método getProduto da classe "+this.getClass());
		}
	}

}