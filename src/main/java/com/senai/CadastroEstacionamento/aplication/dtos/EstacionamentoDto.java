package com.senai.CadastroEstacionamento.aplication.dtos;

import com.senai.CadastroEstacionamento.domain.entity.Estacionamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstacionamentoDto(
        Long id,
        @NotBlank(message = "O endereço não pode ser vazio")
        @Schema(description = "Endereço do estacionamento", example = "Rua das Flores, 123")
        String endereco,
        @NotNull(message = "O número de vagas não pode ser nulo")
        @Schema(description = "Número de vagas do estacionamento", example = "50")
        Integer numeroVagas,
        @NotBlank(message = "O nome não pode ser vazio")
        @Schema(description = "Nome do estacionamento", example = "Estacionamento Central")
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
