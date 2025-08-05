package com.senai.CadastroEstacionamento.domain.repository;

import com.senai.CadastroEstacionamento.domain.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
