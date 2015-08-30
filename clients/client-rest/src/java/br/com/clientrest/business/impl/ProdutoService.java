package br.com.clientrest.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clientrest.business.IProdutoService;
import br.com.clientrest.exception.ProdutoInexistenteException;
import br.com.clientrest.exception.RepositoryException;
import br.com.clientrest.exception.ServiceException;
import br.com.clientrest.integration.IProdutoWS;
import br.com.clientrest.model.Produto;
import br.com.clientrest.model.filter.ProdutoFiltro;
/**
 * Classe de serviço de produto. Implementa a interface IProdutoService
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 03/07/2015 11:45:48
 */
@Service("produtoService")
public class ProdutoService implements IProdutoService {

	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IProdutoWS produtoWS;
	
	@Transactional(readOnly=true)
	public List<Produto> getAll(ProdutoFiltro produtoFiltro) throws ServiceException {
		if (produtoFiltro == null)
			throw new ServiceException("client_rest_erro_filtro_insuficiente");
		try {
			return produtoWS.getAll(produtoFiltro);
		}
		catch(RepositoryException error) {
			log.error("Erro ao acessar o repositório pelo método getAll da classe "+this.getClass(),error);
			throw new ServiceException("client_rest_erro_acesso_repositorio");
		}
	}

	@Transactional(readOnly=true)
	public Produto getProduto(ProdutoFiltro produtoFiltro) throws ServiceException {
		if (produtoFiltro == null)
			throw new ServiceException("fmea8d_erro_filtro_insuficiente");
		try {
			return produtoWS.getProduto(produtoFiltro);
		}
		catch(ProdutoInexistenteException error) {
			throw new ServiceException("client_rest_erro_produto_inexistente");
		}
		catch(RepositoryException error) {
			log.error("Erro ao acessar o repositório pelo método getProduto da classe "+this.getClass(),error);
			throw new ServiceException("client_rest_erro_acesso_repositorio");
		}
	}

}
