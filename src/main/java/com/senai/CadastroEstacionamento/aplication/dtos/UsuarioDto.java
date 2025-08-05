package com.senai.CadastroEstacionamento.aplication.dtos;

import com.senai.CadastroEstacionamento.domain.entity.Cliente;
import com.senai.CadastroEstacionamento.domain.entity.GerenteEstacionamento;
import com.senai.CadastroEstacionamento.domain.entity.Usuario;
import com.senai.CadastroEstacionamento.domain.enums.TipoDeUsuario;

import java.time.LocalDate;

public record UsuarioDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String senha,
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
