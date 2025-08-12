package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.aplication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/{id}/carros")
    public ResponseEntity<List<CarroDto>> listarCarros(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.listarCarrosClientes(id));
    }

    @GetMapping("/{id}/carros/{idCarro}")
    public ResponseEntity<Optional<CarroDto>> listarCarroPorId(@PathVariable Long id, @PathVariable Long idCarro) {
        return ResponseEntity.ok(usuarioService.listarCarroPorId(idCarro));
    }

    @PutMapping("/{id}/carros")
    public ResponseEntity<String> ligarCarroACliente(@PathVariable Long id, @RequestBody CarroDto carroDto) {
        if (usuarioService.ligarCarroACliente(id, carroDto)) {
            return ResponseEntity.ok("Carro adicionado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/carros/{idCarro}")
    public ResponseEntity<String> alterarInfoCarro(@PathVariable Long id, @PathVariable Long idCarro) {
        if (usuarioService.alterarInfoCarro(idCarro)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/carros/{idCarro}")
    public ResponseEntity<String> deletarCarro(@PathVariable Long id, @PathVariable Long idCarro) {
        if (usuarioService.deletarCarro(idCarro)) {
            return ResponseEntity.ok("Ocorrência inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
