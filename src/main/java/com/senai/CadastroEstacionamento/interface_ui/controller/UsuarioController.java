package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.aplication.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Gerencia os usuários do sistema")
@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @Operation(
            summary = "Cadastrar um novo usuario",
            description = "Adiciona um novo usuario à base de dados após validações",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioDto.class),
                            examples = @ExampleObject(value = """
                                        {
                                          "nome": "Pedro Henrique",
                                          "cpf": 11111111111,
                                          "dataNascimento": "2007-03-03",
                                          "email": "pedro@gmail.com",
                                           "senha": "123456",
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Violação de regras de negócio")
            }
    )
    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody List<UsuarioDto> dtos){
        usuarioService.cadastrarUsuarios(dtos);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Buscar usuarios por ID",
            description = "Retorna um usuario existente a partir do seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(UsuarioDto.toDto(usuarioService.buscarPorId(id)));
    }

    @Operation(
            summary = "Listar todos os usuarios",
            description = "Retorna todos os usuarios cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @Operation(
            summary = "Atualizar um usuario",
            description = "Atualiza os dados de um usuario existente com novas informações",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço atualizado"),
                    @ApiResponse(responseCode = "400", description = "Violação de regras de negócio")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        if (usuarioService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(
            summary = "Deletar um usuario",
            description = "Remove um usuario da base de dados a partir do seu ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (usuarioService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
