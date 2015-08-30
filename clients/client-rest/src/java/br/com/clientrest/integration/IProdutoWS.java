package br.com.clientrest.integration;

import java.util.List;

import br.com.clientrest.exception.ProdutoInexistenteException;
import br.com.clientrest.exception.RepositoryException;
import br.com.clientrest.model.Produto;
import br.com.clientrest.model.filter.ProdutoFiltro;
/**
 * Interface de persist�ncia de produto
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 03/07/2015 11:11:20
 */
public interface IProdutoWS {

	/**
	 * M�todo para consultar uma lista de produtos
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 03/07/2015 11:11:51
	 * @param produtoFiltro - Objeto com as informa��es a serem consultadas na pesquisa
	 * @return Retorna uma lista de objetos encontrados ou uma lista vazia caso nenhum objeto corresponda a pesquisa
	 * @throws RepositoryException - Exce��o disparada caso ocorra erro no acesso ao banco
	 */
	public List<Produto> getAll(ProdutoFiltro produtoFiltro) throws RepositoryException;
	
	/**
	 * M�todo para consultar um objeto de produto
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 03/07/2015 11:12:14
	 * @param produtoFiltro - Filtro com os par�metros de familia de produto preenchidos. Inclui tamb�m se � para consultar os exclu�dos
	 * @return Retorna um objeto de produto
	 * @throws ProdutoInexistenteException - Exce��o disparada caso o produto n�o seja encontrado
	 * @throws RepositoryException - Exce��o disparada caso ocorra erro no acesso ao banco
	 */
	public Produto getProduto(ProdutoFiltro produtoFiltro) throws ProdutoInexistenteException, RepositoryException;
			
}
