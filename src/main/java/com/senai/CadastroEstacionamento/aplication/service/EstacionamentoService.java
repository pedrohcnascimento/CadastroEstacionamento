package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.domain.entity.Estacionamento;
import com.senai.CadastroEstacionamento.domain.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    public void cadastrarEstacionamentos(List<EstacionamentoDto> listaDtos) {
        listaDtos.forEach(estacionamentoDto -> {
            Estacionamento estacionamento = estacionamentoDto.fromDto();
            estacionamentoRepository.save(estacionamento);
        });

    }

    public List<EstacionamentoDto> listarEstacionamentos() {
        return estacionamentoRepository.findAll().stream().map(EstacionamentoDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EstacionamentoDto> buscarPorId(Long id) {
        return estacionamentoRepository.findById(id)
                .map(EstacionamentoDto::toDto);
    }

    public boolean atualizar(Long id, EstacionamentoDto dto) {
        return estacionamentoRepository.findById(id).map(estacionamento -> {
            Estacionamento estacionamentoAtualizado = dto.fromDto();
            estacionamento.setNome(estacionamentoAtualizado.getNome());

            estacionamentoRepository.save(estacionamento);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id) {
        return estacionamentoRepository.findById(id).map(estacionamento -> {
            estacionamentoRepository.deleteById(id);
            return true;
        }).orElse(false);
    }


}
