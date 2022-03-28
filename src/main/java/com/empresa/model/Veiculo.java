package com.empresa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.empresa.dto.VeiculoRequestDto;

@Entity
@Table(name = "veiculos")
public class Veiculo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Mome não pode ser vazio")
	@Column(name = "nome")
	private String nome;
	@NotEmpty(message = "Marca não pode ser vazio")
	@Column(name = "marca")
	private String marca;
	@NotEmpty(message = "Modelo não pode ser vazio")
	@Column(name = "modelo")
	private String modelo;
	@NotNull(message = "Data de fabricação não pode ser vazio")
	@Column(name = "fabricacao")
	private LocalDate fabricacao;
	@Column(name = "consumocidade")
	@NotNull(message = "O consumo médio na cidade não pode ser nulo")
	@DecimalMin(value = "0.1", inclusive = false)
	private BigDecimal consumoMedioCidade;
	@Column(name = "consumorodovia")
	@NotNull(message = "O consimo médio na rodovia não pode ser nulo")
	@DecimalMin(value = "0.1", inclusive = false)
	private BigDecimal consumoMedioRodovia;

	public Veiculo(VeiculoRequestDto dto) {
		this.nome = dto.getNome();
		this.marca = dto.getMarca();
		this.modelo = dto.getModelo();
		this.fabricacao = dto.getFabricacao();
		this.consumoMedioCidade = dto.getConsumoMedioCidade();
		this.consumoMedioRodovia = dto.getConsumoMedioRodovia();

	}

	public Veiculo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
