package com.senai.CadastroEstacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected String email;
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    protected List<String> permissoes;
}
