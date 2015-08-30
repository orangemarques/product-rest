package br.com.clientrest.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe para valida��o de endere�o de e-mail
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 08/04/2012 23:51:08
 */
public class ValidaEnderecoEmail {

	/**
	 * M�todo para validar se um endere�o de e-mail � v�lido ou n�o.
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 06/09/2012 23:20:52
	 * @param email - Valor contendo o endere�o de e-mail
	 * @return Retorna um valor booleano. True se endere�o v�lido. False se endere�o inv�lido.
	 */
	public static boolean isValidAddress(String email){
		if ((email == null) || (email.trim().length() <= 3))
			return Boolean.FALSE;
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
}
