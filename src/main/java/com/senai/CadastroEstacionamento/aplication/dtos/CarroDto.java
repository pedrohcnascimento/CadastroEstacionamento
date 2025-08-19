package com.senai.CadastroEstacionamento.aplication.dtos;

import com.senai.CadastroEstacionamento.domain.entity.Carro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CarroDto (
        Long id,
        @NotBlank(message = "A placa não pode ser nula")
        @Schema(description = "Placa do carro", example = "ABC1234")
        String placa,
        @NotBlank(message = "O modelo não pode ser nulo")
        @Schema(description = "Modelo do carro", example = "Fusca")
        String modelo,
        @NotBlank(message = "A cor não pode ser nula")
        @Schema(description = "Cor do carro", example = "Azul")
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
