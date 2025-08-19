package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.domain.entity.Estacionamento;
import com.senai.CadastroEstacionamento.domain.entity.GerenteEstacionamento;
import com.senai.CadastroEstacionamento.domain.exception.IdDesconhecidoException;
import com.senai.CadastroEstacionamento.domain.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteEstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepo;

    @Autowired
    private UsuarioService usuarioService;

    public List<EstacionamentoDto> listarEstacionamentos(Long idGerente) {
        var gerente = usuarioService.buscarPorId(idGerente);
        if (!(gerente instanceof GerenteEstacionamento)) {
            throw new IdDesconhecidoException("Gerente não encontrado com o ID: " + idGerente);
        }
        var gerenteEstacionamento = (GerenteEstacionamento) gerente;
        return gerenteEstacionamento.getEstacionamentos()
                .stream()
                .map(EstacionamentoDto::toDto)
                .toList();
    }

    public Estacionamento listarEstacionamentoPorId(Long idEstacionamento) {
        return estacionamentoRepo.findById(idEstacionamento)
                .orElseThrow(() -> new IdDesconhecidoException("Estacionamento não encontrado com o ID: " + idEstacionamento));
    }

    public boolean ligarEstacionamentoAGerente(Long idGerente, EstacionamentoDto estacionamentoDto) {
        var gerente = usuarioService.buscarPorId(idGerente);
        if (!(gerente instanceof GerenteEstacionamento)) {
            throw new IdDesconhecidoException("Gerente não encontrado com o ID: " + idGerente);
        }
        var gerenteEstacionamento = (GerenteEstacionamento) gerente;
        var estacionamento = estacionamentoDto.fromDto();
        estacionamento.setGerenteEstacionamento(gerenteEstacionamento);
        estacionamentoRepo.save(estacionamento);
        gerenteEstacionamento.getEstacionamentos().add(estacionamento);
        return true;
    }

    public boolean alterarInfoEstacionamento(Long idEstacionamento) {
        var estacionamentoOpt = estacionamentoRepo.findById(idEstacionamento);
        if (estacionamentoOpt.isPresent()) {
            var estacionamento = estacionamentoOpt.get();
            estacionamentoRepo.save(estacionamento);
            return true;
        }
        return false;
    }

    public boolean deletarEstacionamentos(Long idEstacionamento) {
        if (!estacionamentoRepo.existsById(idEstacionamento)) {
            return false;
        }
        estacionamentoRepo.deleteById(idEstacionamento);
        return true;
    }
}

