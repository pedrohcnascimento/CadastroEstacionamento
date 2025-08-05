package com.senai.CadastroEstacionamento.domain.repository;

import com.senai.CadastroEstacionamento.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
