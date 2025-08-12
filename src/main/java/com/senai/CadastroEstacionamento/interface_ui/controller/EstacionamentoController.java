package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {
    @Autowired
    EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity<Void> cadastrarEstacionamento(@RequestBody List<EstacionamentoDto> dtos){
        estacionamentoService.cadastrarEstacionamentos(dtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDto> buscarPorId(@PathVariable Long id) {
        return estacionamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> listarUsuarios(){
        return ResponseEntity.ok(estacionamentoService.listarEstacionamentos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody EstacionamentoDto dto) {
        if (estacionamentoService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (estacionamentoService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
