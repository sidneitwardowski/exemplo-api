package com.empresa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.empresa.model.Veiculo;

public class VeiculoRequestDto {

	@NotEmpty(message = "Mome não pode ser vazio")
	private String nome;
	@NotEmpty(message = "Marca não pode ser vazio")
	@Column(name = "marca")
	private String marca;
	@NotEmpty(message = "Modelo não pode ser vazio")
	private String modelo;
	@NotNull(message = "Data de fabricação não pode ser vazio")
	private LocalDate fabricacao;
	@NotNull(message = "O consumo médio na cidade não deve ser nulo")
	@DecimalMin(value = "0.1", inclusive = false)
	private BigDecimal consumoMedioCidade;
	@NotNull(message = "O consumo médio na rodovia não deve ser nulo")
	@DecimalMin(value = "0.1", inclusive = false)

	private BigDecimal consumoMedioRodovia;

	public VeiculoRequestDto(Veiculo veiculo) {
		this.nome = veiculo.getNome();
		this.marca = veiculo.getMarca();
		this.modelo = veiculo.getModelo();
		this.fabricacao = veiculo.getFabricacao();
		this.consumoMedioCidade = veiculo.getConsumoMedioCidade();
		this.consumoMedioRodovia = veiculo.getConsumoMedioRodovia();
	}

	public VeiculoRequestDto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(LocalDate fabricacao) {
		this.fabricacao = fabricacao;
	}

	public BigDecimal getConsumoMedioCidade() {
		return consumoMedioCidade;
	}

	public void setConsumoMedioCidade(BigDecimal consumoMedioCidade) {
		this.consumoMedioCidade = consumoMedioCidade;
	}

	public BigDecimal getConsumoMedioRodovia() {
		return consumoMedioRodovia;
	}

	public void setConsumoMedioRodovia(BigDecimal consumoMedioRodovia) {
		this.consumoMedioRodovia = consumoMedioRodovia;
	}

}
