package com.empresa.repository;

import com.empresa.model.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
    
}
