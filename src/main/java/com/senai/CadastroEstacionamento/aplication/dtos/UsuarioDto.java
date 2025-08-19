package com.senai.CadastroEstacionamento.aplication.dtos;

import com.senai.CadastroEstacionamento.domain.entity.Cliente;
import com.senai.CadastroEstacionamento.domain.entity.GerenteEstacionamento;
import com.senai.CadastroEstacionamento.domain.entity.Usuario;
import com.senai.CadastroEstacionamento.domain.enums.TipoDeUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UsuarioDto(
        @Schema(description = "ID do usuário", example = "1")
        Long id,
        @NotBlank(message = "O nome não pode ser vazio")
        @Schema(description = "Nome do usuário", example = "João da Silva")
        String nome,
        @NotNull(message = "O CPF não pode ser nulo")
        @DecimalMin(value = "0", message = "O CPF deve ser um número válido")
        @Schema(description = "CPF do usuário", example = "12345678901")
        String cpf,
        @NotNull(message = "A data de nascimento não pode ser nula")
        @Schema(description = "Data de nascimento do usuário", example = "2000-01-01")
        LocalDate dataNascimento,
        @NotBlank(message = "O email não pode ser vazio")
        @Schema(description = "Email do usuário", example = "pedro@gmail.com")
        String email,
        @NotBlank(message = "A senha não pode ser vazia")
        @Schema(description = "Senha do usuário", example = "123456",hidden = true)
        String senha,
        @NotBlank(message = "O tipo de usuário não pode ser vazio")
        @NotNull(message = "O tipo de usuário não pode ser nulo")
        @Schema(description = "Tipo de usuário", example = "CLIENTE", allowableValues = "CLIENTE, GERENTEESTACIONAMENTO")
        TipoDeUsuario tipoDeUsuario
) {

    public static UsuarioDto toDto(Usuario u){
        TipoDeUsuario tipo = switch (u) {
            case Cliente c -> TipoDeUsuario.CLIENTE;
            case GerenteEstacionamento g -> TipoDeUsuario.GERENTEESTACIONAMENTO;
            default -> throw new IllegalArgumentException("Usuário desconhecido detectado.");
        };

        return new UsuarioDto(u.getId(), u.getNome(), u.getCpf(), u.getDataNascimento(),u.getEmail(), u.getSenha(), tipo);
    }

    public Usuario fromDto(){
        Usuario usuario = switch (tipoDeUsuario){
            case CLIENTE -> new Cliente();
            case GERENTEESTACIONAMENTO -> new GerenteEstacionamento();
        };

        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setDataNascimento(dataNascimento);
        usuario.setSenha(senha);

        return usuario;
    }
}
