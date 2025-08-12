package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.domain.entity.Carro;
import com.senai.CadastroEstacionamento.domain.entity.Cliente;
import com.senai.CadastroEstacionamento.domain.entity.Usuario;
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

    public Optional<UsuarioDto> buscarPorId(Long id) {
        return usuarioRepo.findById(id)
                .map(UsuarioDto::toDto);
    }

    public boolean atualizar(Long id, UsuarioDto dto) {
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
        return usuarioRepo.findById(id).map(usuario -> {
            usuarioRepo.deleteById(id);
            return true;
        }).orElse(false);
    }

    //Regras de negócio
    public List<CarroDto> listarCarrosClientes(Long id) {
        return new ArrayList<CarroDto>();
    }

    public Optional<CarroDto> listarCarroPorId(Long idCarro) {
        return carroRepo.findById(idCarro).map(CarroDto::toDto);
    }

    public boolean ligarCarroACliente(Long idCliente, CarroDto carroDto) {
        var cliente = usuarioRepo.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

       return true;
    }

    public boolean alterarInfoCarro(Long idCarro) {
        return true;
    }

    public boolean deletarCarro(Long idCarro) {
       return true;
    }

    public List<EstacionamentoDto> listarEstacionamentos(Long id) {
        return new ArrayList<EstacionamentoDto>();
    }

    public Optional<EstacionamentoDto> listarEstacionamentoPorId(Long idEstacionamento) {
        return estacionamentoRepo.findById(idEstacionamento).map(EstacionamentoDto::toDto);
    }

    public boolean ligarEstacionamentoAGerente(Long id, EstacionamentoDto estacionamentoDto) {
        return true;
    }

    public boolean alterarInfoEstacionamento(Long idEstacionamento) {
        return true;
    }

    public boolean deletarEstacionamentos(Long idEstacionamento) {
        return true;
    }
}
