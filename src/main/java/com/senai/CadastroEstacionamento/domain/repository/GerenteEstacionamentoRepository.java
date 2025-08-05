package com.senai.CadastroEstacionamento.domain.repository;

import com.senai.CadastroEstacionamento.domain.entity.GerenteEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteEstacionamentoRepository extends JpaRepository<GerenteEstacionamento, Long> {
}
