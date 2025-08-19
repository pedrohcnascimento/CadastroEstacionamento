package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.service.GerenteEstacionamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gerentes de Estacionamento", description = "Gerencia as ações que somentes os gerentes de estacionamento podem realizar no sistema")
@RestController
@RequestMapping("/gerentesEstacionamentos")
public class GerenteEstacionamentoController {

    @Autowired
    GerenteEstacionamentoService gerenteEstacionamentoService;

    @GetMapping("/{id}/estacionamentos")
    public ResponseEntity<List<EstacionamentoDto>> listarEstacionamentos(@PathVariable Long id) {
        return ResponseEntity.ok(gerenteEstacionamentoService.listarEstacionamentos(id));
    }

    @GetMapping("/{id}/estacionamentos/{idEstacionamento}")
    public ResponseEntity<EstacionamentoDto> listarOcorrenciaPorId(@PathVariable Long id, @PathVariable Long idEstacionamento) {
        return ResponseEntity.ok(EstacionamentoDto.toDto(gerenteEstacionamentoService.listarEstacionamentoPorId(idEstacionamento)));
    }

    @PutMapping("/{id}/estacionamentos")
    public ResponseEntity<String> ligarEstacionamentoAGerente(@PathVariable Long id, @RequestBody EstacionamentoDto estacionamentoDto) {
        if (gerenteEstacionamentoService.ligarEstacionamentoAGerente(id, estacionamentoDto)) {
            return ResponseEntity.ok("Estacionamento adicionado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estacionamentos/{idEstacionamento}")
    public ResponseEntity<String> alterarInfoEstacionamento(@PathVariable Long id, @PathVariable Long idEstacionamento) {
        if (gerenteEstacionamentoService.alterarInfoEstacionamento(idEstacionamento)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/estacionamentos/{idEstacionamentos}")
    public ResponseEntity<String> deletarEstacionamentos(@PathVariable Long id, @PathVariable Long idEstacionamento) {
        if (gerenteEstacionamentoService.deletarEstacionamentos(idEstacionamento)) {
            return ResponseEntity.ok("Ocorrência inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
