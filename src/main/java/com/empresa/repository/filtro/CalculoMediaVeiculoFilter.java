package com.empresa.repository.filtro;

import java.math.BigDecimal;

public class CalculoMediaVeiculoFilter {
	public BigDecimal precoGasolina;
	public BigDecimal totalKmCidade;
	public BigDecimal totalkmRodovia;

	public CalculoMediaVeiculoFilter(BigDecimal precoGasolina, BigDecimal totalKmCidade, BigDecimal totalkmRodovia) {
		this.precoGasolina = precoGasolina;
		this.totalKmCidade = totalKmCidade;
		this.totalkmRodovia = totalkmRodovia;
	}

	public CalculoMediaVeiculoFilter() {
	}

	public BigDecimal getPrecoGasolina() {
		return precoGasolina;
	}

	public void setPrecoGasolina(BigDecimal precoGasolina) {
		this.precoGasolina = precoGasolina;
	}

	public BigDecimal getTotalKmCidade() {
		return totalKmCidade;
	}

	public void setTotalKmCidade(BigDecimal totalKmCidade) {
		this.totalKmCidade = totalKmCidade;
	}

	public BigDecimal getTotalkmRodovia() {
		return totalkmRodovia;
	}

	public void setTotalkmRodovia(BigDecimal totalkmRodovia) {
		this.totalkmRodovia = totalkmRodovia;
	}

}
