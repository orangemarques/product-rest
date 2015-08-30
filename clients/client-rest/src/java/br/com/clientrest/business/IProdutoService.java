package br.com.clientrest.business;

import java.util.List;

import br.com.clientrest.exception.ServiceException;
import br.com.clientrest.model.Produto;
import br.com.clientrest.model.filter.ProdutoFiltro;

/**
 * Interface de servi�o de produto
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 03/07/2015 11:38:49
 */
public interface IProdutoService {

	/**
	 * M�todo para consultar uma lista de produtos cadastrados
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 03/07/2015 11:39:05
	 * @param familiaProdutoFiltro - Objeto com as informa��es a serem consultadas na pesquisa
	 * @return Retorna uma lista de objetos encontrados ou uma lista vazia caso nenhum objeto corresponda a pesquisa
	 * @throws ServiceException - Exce��o disparada caso ocorra algum problema na camada de servi�o
	 */
	public List<Produto> getAll(ProdutoFiltro produtoFiltro) throws ServiceException;
	
	/**
	 * M�todo para consultar um objeto de produto
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 03/07/2015 11:39:42
	 * @param produtoFiltro - Filtro com o ID do produto.
	 * @return Retorna um objeto de produto
	 * @throws ServiceException - Exce��o disparada caso ocorra algum problema na camada de servi�o
	 */
	public Produto getProduto(ProdutoFiltro produtoFiltro) throws ServiceException;
	
}
