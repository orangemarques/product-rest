package br.com.clientrest.validate;
/**
 * Classe para fazer validação de CPF
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 04/08/2013 15:29:23
 */
public class ValidaCpf {

	public static boolean isValidCpf(String cpf) {
		try {
			if (cpf.length() != 11)
				return Boolean.FALSE;
			Integer[] vetor = new Integer[11];
			int sumDigit = 0;
			int vd1, vd2;
			vd1 = vd2 = 0;
			for (int i=0; i<cpf.length(); i++) {
				vetor[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
			}
			//Cálculo do 1º dígito
			for (int i=0; i<9; i++)
				sumDigit += (10-i)*vetor[i];
			vd1 = sumDigit%11;
			vd1 = (vd1 < 2) ? 0 : 11 - vd1;
			if (vd1 != vetor[9])
				return false;
			//Cálculo do 2º dígito
			sumDigit = 0;
			for (int i=0; i<10; i++)
				sumDigit += (11-i)*vetor[i];
			vd2 = sumDigit%11;
			vd2 = (vd2 < 2) ? 0 : 11 - vd2;
			if (vd2 != vetor[10])
				return false;
			return true;
		}
		catch(Exception error) {
			return false;
		}
	}
	
}
