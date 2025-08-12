package com.senai.CadastroEstacionamento.aplication.dtos;

import com.senai.CadastroEstacionamento.domain.entity.Carro;
import com.senai.CadastroEstacionamento.domain.entity.Estacionamento;

public record EstacionamentoDto(
        Long id,
        String endereco,
        Integer numeroVagas,
        String nome
) {
    public static EstacionamentoDto toDto(Estacionamento e){
        return new EstacionamentoDto(e.getId(), e.getEndereco(), e.getNumeroDeVagas(), e.getNome());
    }

    public Estacionamento fromDto(){
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setId(id);
        estacionamento.setEndereco(endereco);
        estacionamento.setNumeroDeVagas(numeroVagas);
        estacionamento.setNome(nome);

        return estacionamento;
    }
}
