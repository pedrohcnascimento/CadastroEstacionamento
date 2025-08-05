package com.senai.CadastroEstacionamento.domain.repository;

import com.senai.CadastroEstacionamento.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
