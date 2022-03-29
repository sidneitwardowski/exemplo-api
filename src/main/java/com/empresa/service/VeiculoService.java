package com.empresa.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.dto.VeiculoResponseDto;
import com.empresa.dto.builder.VeiculoResponseDtoBuilder;
import com.empresa.exceptions.RegradePrenchimentoException;
import com.empresa.model.Veiculo;
import com.empresa.repository.VeiculoRepository;
import com.empresa.repository.filtro.CalculoMediaVeiculoFilter;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public Veiculo salvar(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);

	}

	public List<VeiculoResponseDto> rankingDeVeiculos(CalculoMediaVeiculoFilter veiculoParametro) {
		var lista = veiculoRepository.findAll();

		if (lista.size() > 0) {
			var retorno = lista.stream()
					.map(veiculo -> new VeiculoResponseDtoBuilder(veiculo).calculaCombustivelGasto(veiculoParametro)
							.calculaValorTotal(veiculoParametro).build())
					.sorted(Comparator.comparingDouble(dto -> dto.getValorGastoCombustivel().doubleValue())).toList();

			return retorno;
		}

		var listaVazia = new LinkedList<VeiculoResponseDto>();
		return listaVazia;

	}

	public void validaFiltro(CalculoMediaVeiculoFilter veiculoParametro) {
		if (veiculoParametro.getPrecoGasolina() == null || veiculoParametro.getPrecoGasolina().doubleValue() == 0)
			throw new RegradePrenchimentoException("É necessário informar o preço da gasolina");
		else if (veiculoParametro.getTotalKmCidade() == null)
			throw new RegradePrenchimentoException("É necessário informar o total de km percorridos na cidade");
		else if (veiculoParametro.getTotalkmRodovia() == null)
			throw new RegradePrenchimentoException("É necessário informar o total de km percorridos na rodovia");
		else if (veiculoParametro.getTotalKmCidade().doubleValue() == 0
				&& veiculoParametro.getTotalkmRodovia().doubleValue() == 0)
			throw new RegradePrenchimentoException(
					"Pelo menos um dos parametros(total de Km percorridos na cidade ou total de Km percorridos na rodovia) deve ser informado");

	}

}
