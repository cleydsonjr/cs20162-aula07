package br.ufg.cs.cleydsonjr;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Auxiliar para obtenção de informações relacionadas a datas
 */
public final class Calendario {

    /**
     * Representação do número zero usado na implementção interna do algoritmo
     */
    private static final int ZERO = 0;

    /**
     * Constante representando o valor do dia da semana Domingo
     */
    public static final int DOMINGO = 6;

    /**
     * Constante representando o valor do dia da semana Segunda
     */
    public static final int SEGUNDA = 0;

    /**
     * Constante guardando a quantidade de dias na semana
     */
    private static final int DIAS_SEMANA = 7;

    /**
     * Constante guardando o valor que será retornado em caso de entrada inválida
     */
    private static final int ENTRADA_INVALIDA = -1;

    /**
     * Constante guardando a representação do padrão esperado de data tratada pela classe
     */
    private static final String FORMATO_PADRAO = "yyyyMMdd";

    /**
     * Formatador padrão para parse das datas informadas
     */
    private static final DateTimeFormatter FORMATADOR_PADRAO;

    static {
        FORMATADOR_PADRAO = DateTimeFormat.forPattern(FORMATO_PADRAO);
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
    public static int diaSemana(int data, int bissexto, int dataConhecida, int diaSemana) {
        int diaSemanaCalculado;

        // Valida as entradas de acordo com as regras fornecidas
        if (dataValida(data) && dataValida(dataConhecida) && anoValido(bissexto) && diaSemanaValido(diaSemana)) {
            // Converte as datas informadas em objetos LocalDate
            LocalDate dataConvertida = convertaData(data);
            LocalDate dataConhecidaConvertida = convertaData(dataConhecida);

            // Calcula o número de dias entre as duas datas
            int diasEntreDatas = Days.daysBetween(dataConhecidaConvertida, dataConvertida).getDays();

            // Atribui inicialmente o dia da semana informado
            diaSemanaCalculado = diaSemana;

            // Se não for o mesmo dia informado, há diferença entre as datas
            if (diasEntreDatas != ZERO) {
                // Apenas o resto da divisão por 7 interessa
                int dias = Math.abs(diasEntreDatas) % DIAS_SEMANA;
                while (dias > ZERO) {
                    if (diasEntreDatas > ZERO) {
                        // Caso o dia informado seja posterior ao conhecido aumenta os dias
                        diaSemanaCalculado++;
                        if (diaSemanaCalculado > DOMINGO) {
                            diaSemanaCalculado = SEGUNDA;
                        }
                    } else {
                        // Caso o dia informado seja anterior ao conhecido diminui os dias
                        diaSemanaCalculado--;
                        if (diaSemanaCalculado < SEGUNDA) {
                            diaSemanaCalculado = DOMINGO;
                        }
                    }
                    dias--;
                }
            }
        } else {
            diaSemanaCalculado = ENTRADA_INVALIDA;
        }

        return diaSemanaCalculado;
    }

    /**
     * Converte um numero inteiro para LocalDate
     *
     * @param data o numero inteiro representando a data no formato esperado
     * @return A data convertida
     */
    private static LocalDate convertaData(Integer data) {
        return FORMATADOR_PADRAO.parseLocalDate(data.toString());
    }

    /**
     * Valida um ano informado
     *
     * @param ano o numero do ano a ser validado
     * @return Se o numero é válido
     */
    private static boolean anoValido(int ano) {
        return ano > ZERO;
    }

    /**
     * Valida um dia da semana informado
     *
     * @param diaSemana o numero do dia da semana a ser validado
     * @return Se o numero é válido
     */
    private static boolean diaSemanaValido(int diaSemana) {
        return diaSemana >= SEGUNDA && diaSemana <= DOMINGO;
    }

    /**
     * Valida uma data informado
     *
     * @param data o numero da data a ser validada
     * @return Se o numero é válido
     */
    private static boolean dataValida(Integer data) {
        int tamanhoEntradaEsperado = 8;
        try {
            String dataString = data.toString();
            FORMATADOR_PADRAO.parseDateTime(dataString);
            return dataString.length() == tamanhoEntradaEsperado;
        } catch (Exception ex) {
            return false;
        }
    }
}
