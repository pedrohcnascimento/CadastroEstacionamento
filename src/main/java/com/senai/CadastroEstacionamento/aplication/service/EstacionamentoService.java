package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.domain.entity.Estacionamento;
import com.senai.CadastroEstacionamento.domain.exception.IdDesconhecidoException;
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

    public Estacionamento buscarPorId(Long id) {
        return estacionamentoRepository.findById(id).orElseThrow(() -> new IdDesconhecidoException("Usuário não encontrado com o ID: " + id));

    }

    public boolean atualizar(Long id, EstacionamentoDto dto) {
        estacionamentoRepository.findById(id).orElseThrow(() -> new IdDesconhecidoException("Usuário não encontrado com o ID: " + id));
        return estacionamentoRepository.findById(id).map(estacionamento -> {
            Estacionamento estacionamentoAtualizado = dto.fromDto();
            estacionamento.setNome(estacionamentoAtualizado.getNome());

            estacionamentoRepository.save(estacionamento);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id) {
        if (!estacionamentoRepository.existsById(id)){
            throw new IdDesconhecidoException("Usuário não encontrado com o ID: " + id);
        }
        estacionamentoRepository.deleteById(id);
        return true;
    }


}
