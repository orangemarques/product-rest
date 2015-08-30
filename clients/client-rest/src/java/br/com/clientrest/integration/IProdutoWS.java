package br.com.clientrest.integration;

import java.util.List;

import br.com.clientrest.exception.ProdutoInexistenteException;
import br.com.clientrest.exception.RepositoryException;
import br.com.clientrest.model.Produto;
import br.com.clientrest.model.filter.ProdutoFiltro;
/**
 * Interface de persistência de produto
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 03/07/2015 11:11:20
 */
public interface IProdutoWS {

	/**
	 * Método para consultar uma lista de produtos
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 03/07/2015 11:11:51
	 * @param produtoFiltro - Objeto com as informações a serem consultadas na pesquisa
	 * @return Retorna uma lista de objetos encontrados ou uma lista vazia caso nenhum objeto corresponda a pesquisa
	 * @throws RepositoryException - Exceção disparada caso ocorra erro no acesso ao banco
	 */
	public List<Produto> getAll(ProdutoFiltro produtoFiltro) throws RepositoryException;
	
	/**
	 * Método para consultar um objeto de produto
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 03/07/2015 11:12:14
	 * @param produtoFiltro - Filtro com os parâmetros de familia de produto preenchidos. Inclui também se é para consultar os excluídos
	 * @return Retorna um objeto de produto
	 * @throws ProdutoInexistenteException - Exceção disparada caso o produto não seja encontrado
	 * @throws RepositoryException - Exceção disparada caso ocorra erro no acesso ao banco
	 */
	public Produto getProduto(ProdutoFiltro produtoFiltro) throws ProdutoInexistenteException, RepositoryException;
			
}
