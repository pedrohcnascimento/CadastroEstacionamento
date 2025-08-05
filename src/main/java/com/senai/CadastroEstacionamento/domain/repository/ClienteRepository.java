package com.senai.CadastroEstacionamento.domain.repository;

import com.senai.CadastroEstacionamento.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
