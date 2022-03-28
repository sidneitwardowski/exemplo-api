package com.empresa.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.empresa.model.Veiculo;

public class VeiculoResponseDtoBuilder {

	private VeiculoResponseDto instance;
	private final BigDecimal mediaCidade;
	private final BigDecimal mediaRodovia;

	public VeiculoResponseDtoBuilder(Veiculo veiculo) {
		this.instance = new VeiculoResponseDto(veiculo);
		this.mediaCidade = veiculo.getConsumoMedioCidade();
		this.mediaRodovia = veiculo.getConsumoMedioRodovia();
	}

	public VeiculoResponseDtoBuilder calculaCombustivelGasto(VeiculoParametro veiculoPesquisado) {
		BigDecimal totalLitrosCidade = totalGastoEmLitros(veiculoPesquisado.getTotalKmCidade(), mediaCidade);
		BigDecimal totalListrosRodovia = totalGastoEmLitros(veiculoPesquisado.getTotalkmRodovia(), mediaRodovia);
		this.instance.setQuantidadeGastoCombustivel(
				totalLitrosCidade.add(totalListrosRodovia).setScale(2, RoundingMode.HALF_EVEN));
		return this;
	}

	public VeiculoResponseDtoBuilder calculaValorTotal(VeiculoParametro veiculoPesquisado) {

		BigDecimal totalValorCidade = totalGastoEmLitros(veiculoPesquisado.getTotalKmCidade(), mediaCidade)
				.multiply(veiculoPesquisado.getPrecoGasolina());
		BigDecimal totalValorRodovia = totalGastoEmLitros(veiculoPesquisado.getTotalkmRodovia(), mediaRodovia)
				.multiply(veiculoPesquisado.getPrecoGasolina());
		this.instance
				.setValorGastoCombustivel(totalValorCidade.add(totalValorRodovia).setScale(2, RoundingMode.HALF_EVEN));
		return this;
	}

	public VeiculoResponseDto build() {
		return this.instance;
	}

	private BigDecimal totalGastoEmLitros(BigDecimal totalKmPercorrido, BigDecimal media) {
		BigDecimal resultado = BigDecimal.ZERO;
		resultado = totalKmPercorrido.divide(media, RoundingMode.HALF_EVEN);
		return resultado;

	}

}
