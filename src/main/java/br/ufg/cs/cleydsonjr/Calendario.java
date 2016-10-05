package br.ufg.cs.cleydsonjr;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Calendario {
	private static final String FORMATO_PADRAO = "yyyyMMdd";
	private static final int ENTRADA_INVALIDA = -1;
	private DateTimeFormatter formatoData;

	public Calendario() {
		this.formatoData = DateTimeFormat.forPattern(FORMATO_PADRAO);
	}

	/**
	 * Metodo reponsável por obter o dia da semana de uma data fornecida com base em parâmetros fornecidos
	 * O valor 0 para indicar que a primeira data fornecida ocorre em uma segunda-feira, 1
	 * para indicar que essa data ocorre em uma terça-feira, e assim sucessivamente, até 6,
	 * que representa domingo.
	 * O valor -1 será retornado em algumas situações excepcionais de entrada, indicadas abaixo.
	 *
	 * @param data          A data para se obter o dia da semana no formato yyyyMMdd
	 * @param bissexto      Um inteiro maior que zero correspondente a um ano bissexto.
	 * @param dataConhecida Uma data conhecida
	 * @param diaSemana     O dia da semana correspondente à data conhecida
	 * @return O dia da semana da data ou o -1
	 */
	public int diaSemana(int data, int bissexto, int dataConhecida, int diaSemana) {
		int retorno;

		// Valida as entradas de acordo com as regras fornecidas
		if (dataValida(data) && dataValida(dataConhecida) && anoValido(bissexto) && diaSemanaValido(diaSemana)) {
			// Converte as datas informadas em objetos LocalDate
			LocalDate dataConvertida = convertaData(data);
			LocalDate dataConhecidaConvertida = convertaData(dataConhecida);

			// Calcula o número de dias entre as duas datas
			int diasEntreDatas = Days.daysBetween(dataConhecidaConvertida, dataConvertida).getDays();

			if (dataConhecidaConvertida.isEqual(dataConvertida)) {
				retorno = diaSemana;
			} else {
				if (diasEntreDatas > 0) {
					retorno = diasEntreDatas;
					if (retorno > 7) {
						retorno = (retorno % 7) - 7 + diaSemana;
					} else {
						retorno = retorno - 7 + diaSemana;
					}
				} else {
					retorno = Math.abs(diasEntreDatas);
					if (retorno > 7) {
						retorno = (retorno % 7) - 1 + diaSemana;
					} else {
						retorno = retorno + diaSemana;
					}
				}
			}
		} else {
			retorno = ENTRADA_INVALIDA;
		}

		return retorno;
	}

	private LocalDate convertaData(Integer data) {
		return formatoData.parseLocalDate(data.toString());
	}

	private boolean anoValido(int ano) {
		return ano > 0;
	}

	private boolean diaSemanaValido(int diaSemana) {
		return diaSemana >= 0 && diaSemana <= 6;
	}

	private boolean dataValida(Integer data) {
		try {
			String dataString = data.toString();
			formatoData.parseDateTime(dataString);
			return dataString.length() == 8;
		} catch (Exception ex) {
			return false;
		}
	}
}
