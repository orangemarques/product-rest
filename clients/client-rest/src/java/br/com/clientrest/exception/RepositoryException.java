package br.com.clientrest.exception;
/**
 * Classe de exceção disparada pela camada de persistência
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 24/08/2012 15:11:22
 */
public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = -2117973120823735785L;

	public RepositoryException(){
		super();
	}

	public RepositoryException(String message){
		super(message);
	}
	
	public RepositoryException(Throwable cause){
		super(cause);
	}
	
	public RepositoryException(String message, Throwable cause){
		super(message, cause);
	}
	
}
