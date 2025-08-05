package com.senai.CadastroEstacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String endereco;
    protected Integer numeroDeVagas;
    protected String nome;

    @ManyToOne
    @JoinColumn(name = "gerente_estacionamento_id")
    private GerenteEstacionamento gerenteEstacionamento;
}
