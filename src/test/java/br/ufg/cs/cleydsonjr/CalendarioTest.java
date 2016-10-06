package br.ufg.cs.cleydsonjr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalendarioTest {
	Calendario calendario;

	@Before
	public void setUp() throws Exception {
		calendario = new Calendario();
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataInvalida2010101() throws Exception {
		int diaSemana = calendario.diaSemana(2010101, 2016, 20161005, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataInvalida100000101() throws Exception {
		int diaSemana = calendario.diaSemana(100000101, 2016, 20161005, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataInvalida20161301() throws Exception {
		int diaSemana = calendario.diaSemana(20161301, 2016, 20161005, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataInvalida20160001() throws Exception {
		int diaSemana = calendario.diaSemana(20161301, 2016, 20161005, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaAnoInvalidoZero() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 0, 20161005, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaAnoInvalidoNegativo() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, -100, 20161005, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataConhecidaInvalida2010101() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 2016, 2010101, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataConhecidaInvalida100000101() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 2016, 100000101, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDataConhecidaInvalida20161301() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 2016, 20161301, 2);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDiaSemanaConhecidoMenorQueZero() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 2016, 20161005, -1);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoErroParaDiaSemanaConhecidoMaiorQueSeis() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 2016, 20161005, 7);

		Assert.assertEquals("Deveria ter retornado -1", -1, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido1() throws Exception {
		int diaSemana = calendario.diaSemana(20160928, 2016, 20160928, 2);

		Assert.assertEquals("Deveria ter retornado dia da semana 2 (quarta)", 2, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido2() throws Exception {
		int diaSemana = calendario.diaSemana(20160928, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 2 (quarta)", 2, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido3() throws Exception {
		int diaSemana = calendario.diaSemana(20160908, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 3 (quinta)", 3, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido4() throws Exception {
		int diaSemana = calendario.diaSemana(20161005, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 2 (quarta)", 2, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido5() throws Exception {
		int diaSemana = calendario.diaSemana(20160807, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 6 (domingo)", 6, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido6() throws Exception {
		int diaSemana = calendario.diaSemana(20160829, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 0 (segunda)", 0, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido7() throws Exception {
		int diaSemana = calendario.diaSemana(20161214, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 2 (quarta)", 2, diaSemana);
	}

	@Test
	public void diaSemanaRetornaResultadoCorretoParaCenarioConhecido8() throws Exception {
		int diaSemana = calendario.diaSemana(20160831, 2016, 20160901, 3);

		Assert.assertEquals("Deveria ter retornado dia da semana 2 (quarta)", 2, diaSemana);
	}
}