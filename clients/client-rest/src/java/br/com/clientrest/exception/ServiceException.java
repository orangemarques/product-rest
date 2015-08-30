package br.com.clientrest.exception;

import java.util.List;

/**
 * Classe de exceção disparada pela camada de serviço
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 23/08/2012 21:30:12
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -6837125593375509680L;

	private List<String> messages;
	
	public ServiceException(){
		super();
	}

	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(Throwable cause){
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ServiceException(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}
	
}
