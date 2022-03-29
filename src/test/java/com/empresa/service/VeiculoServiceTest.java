package com.empresa.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.empresa.dto.VeiculoRequestDto;
import com.empresa.dto.VeiculoResponseDto;
import com.empresa.dto.builder.VeiculoResponseDtoBuilder;
import com.empresa.model.Veiculo;
import com.empresa.repository.VeiculoRepository;
import com.empresa.repository.filtro.CalculoMediaVeiculoFilter;

@Sql(value = "/CargaInicial.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
@Transactional
public class VeiculoServiceTest {

	@Autowired
	private VeiculoService service;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Test
	void deveCadastrarVeiculo() {
		var dto = new VeiculoRequestDto();
		dto.setConsumoMedioCidade(BigDecimal.TEN);
		dto.setConsumoMedioRodovia(new BigDecimal(13.00));
		dto.setFabricacao(LocalDate.now());
		dto.setModelo("Longitude");
		dto.setNome("Jeep Compass");
		dto.setMarca("Jeep");
		assertNotNull(service.salvar(new Veiculo(dto)));
	}

	@Test
	void deveGerarVeiculoResponseDtoBuilder() {
		Veiculo amostra = veiculoRepository.findAll().get(0);
		var filtro = new CalculoMediaVeiculoFilter();
		filtro.setPrecoGasolina(BigDecimal.TEN);
		filtro.setTotalKmCidade(BigDecimal.ONE);
		filtro.setTotalkmRodovia(BigDecimal.TEN);
		var veiculo = veiculoRepository.findById(amostra.getId());
		Optional<VeiculoResponseDto> retorno = Optional.ofNullable(new VeiculoResponseDtoBuilder(veiculo.get())
				.calculaCombustivelGasto(filtro).calculaValorTotal(filtro).build());

		assertTrue(retorno.isPresent());

	}

	@Test
	void deveCalcularCombustivelCorretamente() {
		var veiculo = veiculoRepository.findByNome("Fusca");
		var filtro = new CalculoMediaVeiculoFilter(new BigDecimal(10.00), new BigDecimal(100.00),
				new BigDecimal(100.00));
		var retorno = new VeiculoResponseDtoBuilder(veiculo).calculaCombustivelGasto(filtro).calculaValorTotal(filtro)
				.build();
		assertTrue(retorno.getQuantidadeGastoCombustivel().compareTo(new BigDecimal(22.500)) == 0);

	}

	@Test
	void deveCalcularValorTotalCorretamente() {
		var veiculo = veiculoRepository.findByNome("Fusca");
		var filtro = new CalculoMediaVeiculoFilter(new BigDecimal(10.00), new BigDecimal(100.00),
				new BigDecimal(100.00));
		var retorno = new VeiculoResponseDtoBuilder(veiculo).calculaCombustivelGasto(filtro).calculaValorTotal(filtro)
				.build();
		assertTrue(retorno.getValorGastoCombustivel().compareTo(new BigDecimal(225.00)) == 0);

	}

	@Test
	void deveGerarRankingDeMenorValorGastoCorretamente() {
		var veiculos = veiculoRepository.findAll();
		var filtro = new CalculoMediaVeiculoFilter(new BigDecimal(10.00), new BigDecimal(100.00),
				new BigDecimal(100.00));
		var lista = veiculos.stream().map(veiculo -> new VeiculoResponseDtoBuilder(veiculo)
				.calculaCombustivelGasto(filtro).calculaValorTotal(filtro).build()).toList();

		var menorValor = lista.stream()
				.min(Comparator.comparingDouble(dto -> dto.getValorGastoCombustivel().doubleValue()));
		assertTrue(menorValor.get().getValorGastoCombustivel().compareTo(new BigDecimal("225.000")) == 0);
	}

}
