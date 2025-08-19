package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.domain.entity.Cliente;
import com.senai.CadastroEstacionamento.domain.repository.CarroRepository;
import com.senai.CadastroEstacionamento.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private CarroRepository carroRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<CarroDto> listarCarrosClientes(Long idCliente) {
        var cliente = usuarioRepo.findById(idCliente).filter(u ->
                u instanceof Cliente)
                .map(u -> (Cliente) u)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getCarrosPossuidos().stream().map(CarroDto::toDto).toList();
    }

    public Optional<CarroDto> listarCarroPorId(Long idCarro) {
        return carroRepo.findById(idCarro).map(CarroDto::toDto);
    }

    public boolean ligarCarroACliente(Long idCliente, CarroDto carroDto) {
        var cliente = usuarioRepo.findById(idCliente).filter(u ->
                u instanceof Cliente)
                .map(u -> (Cliente) u)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        var carro = carroDto.fromDto();
        carro.setCliente(cliente);
        carroRepo.save(carro);
        cliente.getCarrosPossuidos().add(carro);
        usuarioRepo.save(cliente);
        return true;
    }

    public boolean alterarInfoCarro(Long idCarro) {
        var carroOpt = carroRepo.findById(idCarro);
        if (carroOpt.isPresent()) {
            var carro = carroOpt.get();
            carroRepo.save(carro);
            return true;
        }
        return false;
    }

    public boolean deletarCarro(Long idCarro) {
        if (!carroRepo.existsById(idCarro)) {
            return false;
        }
        carroRepo.deleteById(idCarro);
        return true;
    }
}
