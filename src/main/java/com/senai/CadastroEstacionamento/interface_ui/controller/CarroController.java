package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.aplication.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    CarroService carroService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCarro(@RequestBody List<CarroDto> dtos){
        carroService.cadastrarCarros(dtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDto> buscarPorId(@PathVariable Long id) {
        return carroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarroDto>> listarCarros(){
        return ResponseEntity.ok(carroService.listarCarros());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody CarroDto dto) {
        if (carroService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (carroService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
