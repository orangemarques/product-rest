package br.com.clientrest.validate;

import java.util.Date;
/**
 * Classe com m�todo para valida��o de per�odos
 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
 * @since 12/07/2013 14:54:52
 */
public class ValidaPeriodo {

	/**
	 * M�todo para validar se uma data de refer�ncia � ap�s um per�odo informado
	 * @author Sylvio Romero <a href="mailto:sylvio.romero@gmail.com">sylvio.romero@gmail.com</a>
	 * @since 12/07/2013 15:09:22
	 * @param dataReferencia - Data de refer�ncia
	 * @param periodoInicio - Per�odo inicial
	 * @param periodoTermino - Per�odo final
	 * @return Retorna true caso seja verdadeiro, false caso seja falso
	 * @throws IllegalArgumentException - Exce��o disparada caso um dos par�metros informados seja nulo.
	 */
	public static boolean isDataReferenciaPosteriorPeriodo(Date dataReferencia, Date periodoInicio, Date periodoTermino) throws IllegalArgumentException {
		if (dataReferencia == null)
			throw new IllegalArgumentException("nfc_erro_util_data_referencia_nulo");
		if (periodoInicio == null)
			throw new IllegalArgumentException("nfc_erro_util_periodo_inicio_nulo");
		if (periodoTermino == null)
			throw new IllegalArgumentException("nfc_erro_util_periodo_termino_nulo");
		if (periodoInicio.after(periodoTermino))
			throw new IllegalArgumentException("nfc_erro_util_periodo_inicio_maior_periodo_termino");
		return dataReferencia.after(periodoTermino) ? Boolean.TRUE : Boolean.FALSE;
	}
	
}
