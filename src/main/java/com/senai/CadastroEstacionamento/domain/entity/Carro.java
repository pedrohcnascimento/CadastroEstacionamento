package com.senai.CadastroEstacionamento.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String placaDoCarro;
    protected String modeloDoCarro;
    protected String corDoCarro;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
