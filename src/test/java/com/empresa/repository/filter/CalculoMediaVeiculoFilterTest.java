package com.empresa.repository.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.empresa.repository.filtro.CalculoMediaVeiculoFilter;
import com.empresa.service.VeiculoService;

@SpringBootTest
public class CalculoMediaVeiculoFilterTest {

	@Autowired
	private VeiculoService service;

	@Test
	void deveValidarPrecoCombustivelMaiorQueZero() {
		Exception exception = assertThrows(Exception.class, () -> {
			var filtro = new CalculoMediaVeiculoFilter();
			filtro.setPrecoGasolina(BigDecimal.ZERO);
			service.validaFiltro(filtro);
		});
		assertEquals("É necessário informar o preço da gasolina", exception.getMessage());

	}

	@Test
	void deveValidarPrecoCombustivelNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			var filtro = new CalculoMediaVeiculoFilter();
			filtro.setPrecoGasolina(null);
			service.validaFiltro(filtro);
		});
		assertEquals("É necessário informar o preço da gasolina", exception.getMessage());

	}

	@Test
	void deveValidarTotalKmCidade() {
		Exception exception = assertThrows(Exception.class, () -> {
			var filtro = new CalculoMediaVeiculoFilter();
			filtro.setPrecoGasolina(BigDecimal.TEN);
			filtro.setTotalKmCidade(null);
			service.validaFiltro(filtro);
		});
		assertEquals("É necessário informar o total de km percorridos na cidade", exception.getMessage());

	}

	@Test
	void deveValidarTotalKmRodovia() {
		Exception exception = assertThrows(Exception.class, () -> {
			var filtro = new CalculoMediaVeiculoFilter();
			filtro.setPrecoGasolina(BigDecimal.TEN);
			filtro.setTotalKmCidade(BigDecimal.ONE);
			filtro.setTotalkmRodovia(null);
			service.validaFiltro(filtro);
		});
		assertEquals("É necessário informar o total de km percorridos na rodovia", exception.getMessage());

	}

	@Test
	void deveValidarTotalKmZerado() {
		Exception exception = assertThrows(Exception.class, () -> {
			var filtro = new CalculoMediaVeiculoFilter();
			filtro.setPrecoGasolina(BigDecimal.TEN);
			filtro.setTotalKmCidade(BigDecimal.ZERO);
			filtro.setTotalkmRodovia(BigDecimal.ZERO);
			service.validaFiltro(filtro);
		});
		assertEquals(
				"Pelo menos um dos parametros(total de Km percorridos na cidade ou total de Km percorridos na rodovia) deve ser informado",
				exception.getMessage());

	}

}
