package com.empresa.dto;

import java.math.BigDecimal;

public class VeiculoParametro {
	public BigDecimal precoGasolina;
	public BigDecimal totalKmCidade;
	public BigDecimal totalkmRodovia;

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
