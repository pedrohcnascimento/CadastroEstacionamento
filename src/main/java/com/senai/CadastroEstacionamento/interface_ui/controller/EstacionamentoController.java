package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.aplication.service.EstacionamentoService;
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

@Tag(name = "Estacionamentos", description = "Gerencia os estacionamentos do sistema")
@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {
    @Autowired
    EstacionamentoService estacionamentoService;

    @Operation(
            summary = "Cadastrar um novo estacionamento",
            description = "Adiciona um novo estacionamento à base de dados após validações",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioDto.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "endereco": "Rua Principal, 123",
                                            "numeroVagas": 50,
                                            "nome": "Estacionamento Central"
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
    public ResponseEntity<Void> cadastrarEstacionamento(@RequestBody List<EstacionamentoDto> dtos){
        estacionamentoService.cadastrarEstacionamentos(dtos);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Buscar estacionamento por ID",
            description = "Retorna um estacionamento existente a partir do seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estacionamento encontrado"),
                    @ApiResponse(responseCode = "404", description = "Estacionamento não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(EstacionamentoDto.toDto(estacionamentoService.buscarPorId(id)));

    }
    @Operation(
            summary = "Listar todos os estacionamentos",
            description = "Retorna todos os estacionamentos cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> listarUsuarios(){
        return ResponseEntity.ok(estacionamentoService.listarEstacionamentos());
    }
    @Operation(
            summary = "Atualizar um estacionamento",
            description = "Atualiza os dados de um estacionamento existente com novas informações",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estacionamento atualizado"),
                    @ApiResponse(responseCode = "400", description = "Violação de regras de negócio")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody EstacionamentoDto dto) {
        if (estacionamentoService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(
            summary = "Deletar um estacionamento",
            description = "Remove um estacionamento da base de dados a partir do seu ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Estacionamento removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Estacionamento não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (estacionamentoService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
