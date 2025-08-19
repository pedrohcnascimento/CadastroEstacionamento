package com.senai.CadastroEstacionamento.aplication.service;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.domain.entity.Carro;
import com.senai.CadastroEstacionamento.domain.exception.IdDesconhecidoException;
import com.senai.CadastroEstacionamento.domain.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public void cadastrarCarros(List<CarroDto> listaDtos) {
        listaDtos.forEach(carroDto -> {
            Carro carro = carroDto.fromDto();
            carroRepository.save(carro);
        });

    }

    public List<CarroDto> listarCarros() {
        return carroRepository.findAll().stream().map(CarroDto::toDto)
                .collect(Collectors.toList());
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(() -> new IdDesconhecidoException("Usuário não encontrado com o ID: " + id));

    }

    public boolean atualizar(Long id, CarroDto dto) {
        carroRepository.findById(id).orElseThrow(() -> new IdDesconhecidoException("Usuário não encontrado com o ID: " + id));

        return carroRepository.findById(id).map(carro -> {
            Carro carroAtualizado = dto.fromDto();

            carroRepository.save(carro);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id) {
        if (!carroRepository.existsById(id)){
            throw new IdDesconhecidoException("Usuário não encontrado com o ID: " + id);
        }
        carroRepository.deleteById(id);
        return true;
    }

}
