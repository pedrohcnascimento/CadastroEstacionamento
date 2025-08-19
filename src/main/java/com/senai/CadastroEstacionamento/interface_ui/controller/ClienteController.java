package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.aplication.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Clientes", description = "Gerencia as ações que somente clientes realizam no sistema")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/{id}/carros")
    public ResponseEntity<List<CarroDto>> listarCarros(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarCarrosClientes(id));
    }

    @GetMapping("/{id}/carros/{idCarro}")
    public ResponseEntity<Optional<CarroDto>> listarCarroPorId(@PathVariable Long id, @PathVariable Long idCarro) {
        return ResponseEntity.ok(clienteService.listarCarroPorId(idCarro));
    }

    @PutMapping("/{id}/carros")
    public ResponseEntity<String> ligarCarroACliente(@PathVariable Long id, @RequestBody CarroDto carroDto) {
        if (clienteService.ligarCarroACliente(id, carroDto)) {
            return ResponseEntity.ok("Carro adicionado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/carros/{idCarro}")
    public ResponseEntity<String> alterarInfoCarro(@PathVariable Long id, @PathVariable Long idCarro) {
        if (clienteService.alterarInfoCarro(idCarro)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/carros/{idCarro}")
    public ResponseEntity<String> deletarCarro(@PathVariable Long id, @PathVariable Long idCarro) {
        if (clienteService.deletarCarro(idCarro)) {
            return ResponseEntity.ok("Ocorrência inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
