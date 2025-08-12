package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentesEstacionamentos")
public class GerenteEstacionamentoController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/{id}/estacionamentos")
    public ResponseEntity<List<EstacionamentoDto>> listarEstacionamentos(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.listarEstacionamentos(id));
    }

    @GetMapping("/{id}/estacionamentos/{idEstacionamento}")
    public ResponseEntity<Optional<EstacionamentoDto>> listarOcorrenciaPorId(@PathVariable Long id, @PathVariable Long idEstacionamento) {
        return ResponseEntity.ok(usuarioService.listarEstacionamentoPorId(idEstacionamento));
    }

    @PutMapping("/{id}/estacionamentos")
    public ResponseEntity<String> ligarEstacionamentoAGerente(@PathVariable Long id, @RequestBody EstacionamentoDto estacionamentoDto) {
        if (usuarioService.ligarEstacionamentoAGerente(id, estacionamentoDto)) {
            return ResponseEntity.ok("Ocorrência de saída adiantada adicionada!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estacionamentos/{idEstacionamento}")
    public ResponseEntity<String> alterarInfoEstacionamento(@PathVariable Long id, @PathVariable Long idEstacionamento) {
        if (usuarioService.alterarInfoEstacionamento(idEstacionamento)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/estacionamentos/{idEstacionamentos}")
    public ResponseEntity<String> deletarEstacionamentos(@PathVariable Long id, @PathVariable Long idEstacionamento) {
        if (usuarioService.deletarEstacionamentos(idEstacionamento)) {
            return ResponseEntity.ok("Ocorrência inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
