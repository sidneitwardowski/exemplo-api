package com.empresa.dto.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.empresa.dto.VeiculoResponseDto;
import com.empresa.model.Veiculo;
import com.empresa.repository.filtro.CalculoMediaVeiculoFilter;

public class VeiculoResponseDtoBuilder {

	private VeiculoResponseDto instance;
	private final BigDecimal mediaCidade;
	private final BigDecimal mediaRodovia;

	public VeiculoResponseDtoBuilder(Veiculo veiculo) {
		this.instance = new VeiculoResponseDto(veiculo);
		this.mediaCidade = veiculo.getConsumoMedioCidade();
		this.mediaRodovia = veiculo.getConsumoMedioRodovia();
	}

	public VeiculoResponseDtoBuilder calculaCombustivelGasto(CalculoMediaVeiculoFilter veiculoFiltro) {
		BigDecimal totalLitrosCidade = totalGastoEmLitros(veiculoFiltro.getTotalKmCidade(), mediaCidade);
		BigDecimal totalListrosRodovia = totalGastoEmLitros(veiculoFiltro.getTotalkmRodovia(), mediaRodovia);
		this.instance.setQuantidadeGastoCombustivel(totalLitrosCidade.add(totalListrosRodovia));
		return this;
	}

	public VeiculoResponseDtoBuilder calculaValorTotal(CalculoMediaVeiculoFilter veiculoPesquisado) {

		BigDecimal totalValorCidade = totalGastoEmLitros(veiculoPesquisado.getTotalKmCidade(), mediaCidade)
				.multiply(veiculoPesquisado.getPrecoGasolina());
		BigDecimal totalValorRodovia = totalGastoEmLitros(veiculoPesquisado.getTotalkmRodovia(), mediaRodovia)
				.multiply(veiculoPesquisado.getPrecoGasolina());
		this.instance.setValorGastoCombustivel(totalValorCidade.add(totalValorRodovia).setScale(2, RoundingMode.UP));
		return this;
	}

	public VeiculoResponseDto build() {
		return this.instance;
	}

	private BigDecimal totalGastoEmLitros(BigDecimal totalKmPercorrido, BigDecimal media) {
		BigDecimal resultado = BigDecimal.ZERO;
		resultado = totalKmPercorrido.divide(media, 3, RoundingMode.UP);
		return resultado;

	}

}
