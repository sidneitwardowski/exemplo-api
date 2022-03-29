package com.empresa.Resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.dto.VeiculoRequestDto;
import com.empresa.model.Veiculo;
import com.empresa.repository.VeiculoRepository;
import com.empresa.repository.filtro.CalculoMediaVeiculoFilter;
import com.empresa.service.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("veiculos")
public class VeiculoResource {

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Operation(summary = "Cadastro de um novo veiculo")
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody VeiculoRequestDto dto) throws Exception {
		var veiculo = new Veiculo(dto);
		veiculoService.salvar(veiculo);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@Operation(summary = "Ranking de veículos que menos gastaram")
	@GetMapping("/ranking-consumo")
	public ResponseEntity<?> rankingVeiculos(CalculoMediaVeiculoFilter dadosVeiculo) {
		veiculoService.validaFiltro(dadosVeiculo);
		return veiculoRepository.count() > 0 ? ResponseEntity.ok(veiculoService.rankingDeVeiculos(dadosVeiculo))
				: ResponseEntity.noContent().build();
	}

}
