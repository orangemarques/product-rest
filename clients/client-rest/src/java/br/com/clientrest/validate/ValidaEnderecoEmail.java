package br.com.clientrest.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe para validação de endereço de e-mail
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 08/04/2012 23:51:08
 */
public class ValidaEnderecoEmail {

	/**
	 * Método para validar se um endereço de e-mail é válido ou não.
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 06/09/2012 23:20:52
	 * @param email - Valor contendo o endereço de e-mail
	 * @return Retorna um valor booleano. True se endereço válido. False se endereço inválido.
	 */
	public static boolean isValidAddress(String email){
		if ((email == null) || (email.trim().length() <= 3))
			return Boolean.FALSE;
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
}
