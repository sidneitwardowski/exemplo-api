package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

	public Veiculo findByNome(String nome);

}
