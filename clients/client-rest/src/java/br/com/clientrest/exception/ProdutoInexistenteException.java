package br.com.clientrest.exception;
/**
 * Classe de exceção disparada quando um produto não é encontrado
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 03/12/2013 22:57:30
 */
public class ProdutoInexistenteException extends Throwable {

	private static final long serialVersionUID = -4248644916337728563L;

	public ProdutoInexistenteException(){
		super();
	}

	public ProdutoInexistenteException(String message){
		super(message);
	}
	
	public ProdutoInexistenteException(Throwable cause){
		super(cause);
	}
	
	public ProdutoInexistenteException(String message, Throwable cause){
		super(message, cause);
	}
	
}
