package com.empresa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.empresa.model.Veiculo;

public class VeiculoResponseDto {

	private final String nome;
	private final String marca;
	private final String modelo;
	private final LocalDate ano;
	private BigDecimal quantidadeGastoCombustivel;
	private BigDecimal valorGastoCombustivel;

	public VeiculoResponseDto(Veiculo veiculo) {
		this.nome = veiculo.getNome();
		this.marca = veiculo.getMarca();
		this.modelo = veiculo.getModelo();
		this.ano = veiculo.getFabricacao();
		this.quantidadeGastoCombustivel = BigDecimal.ZERO;
		this.valorGastoCombustivel = BigDecimal.ZERO;

	}

	public BigDecimal getQuantidadeGastoCombustivel() {
		return quantidadeGastoCombustivel;
	}

	public void setQuantidadeGastoCombustivel(BigDecimal quantidadeGastoCombustivel) {
		this.quantidadeGastoCombustivel = quantidadeGastoCombustivel;
	}

	public BigDecimal getValorGastoCombustivel() {
		return valorGastoCombustivel;
	}

	public void setValorGastoCombustivel(BigDecimal valorGastoCombustivel) {
		this.valorGastoCombustivel = valorGastoCombustivel;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public LocalDate getAno() {
		return ano;
	}

}
