package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.domain.entity.Carro;
import com.senai.CadastroEstacionamento.domain.entity.Cliente;
import com.senai.CadastroEstacionamento.domain.entity.Usuario;
import com.senai.CadastroEstacionamento.domain.exception.IdDesconhecidoException;
import com.senai.CadastroEstacionamento.domain.repository.CarroRepository;
import com.senai.CadastroEstacionamento.domain.repository.EstacionamentoRepository;
import com.senai.CadastroEstacionamento.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private CarroRepository carroRepo;
    
    @Autowired
    private EstacionamentoRepository estacionamentoRepo;

    public void cadastrarUsuarios(List<UsuarioDto> listaDtos) {
        listaDtos.forEach(usuarioDto -> {
            Usuario usuario = usuarioDto.fromDto();
            usuarioRepo.save(usuario);
        });
        
    }

    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepo.findAll().stream().map(UsuarioDto::toDto)
                .collect(Collectors.toList());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepo.findById(id).orElseThrow(() -> new IdDesconhecidoException("Usuário não encontrado com o ID: " + id));
    }

    public boolean atualizar(Long id, UsuarioDto dto) {
        usuarioRepo.findById(id).orElseThrow(() -> new IdDesconhecidoException("Usuário não encontrado com o ID: " + id));
        return usuarioRepo.findById(id).map(usuario -> {
            Usuario usuarioAtualizado = dto.fromDto();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuarioRepo.save(usuario);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id) {
        if (!usuarioRepo.existsById(id)){
            throw new IdDesconhecidoException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepo.deleteById(id);
        return true;
    }
}
