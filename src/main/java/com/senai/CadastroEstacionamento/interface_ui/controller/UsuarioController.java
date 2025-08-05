package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.aplication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody List<UsuarioDto> dtos){
        usuarioService.cadastrarUsuarios(dtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        if (usuarioService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (usuarioService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
