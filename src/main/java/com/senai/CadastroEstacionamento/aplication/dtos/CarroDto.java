package com.senai.CadastroEstacionamento.aplication.dtos;

import com.senai.CadastroEstacionamento.domain.entity.Carro;

public record CarroDto (
        Long id,
        String placa,
        String modelo,
        String cor
){
    public static CarroDto toDto(Carro c){
        return new CarroDto(c.getId(), c.getPlacaDoCarro(), c.getModeloDoCarro(), c.getCorDoCarro());
    }

    public Carro fromDto(){
        Carro carro = new Carro();
        carro.setId(id);
        carro.setPlacaDoCarro(placa);
        carro.setModeloDoCarro(modelo);
        carro.setCorDoCarro(cor);

        return carro;
    }
}
