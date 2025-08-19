package com.senai.CadastroEstacionamento.interface_ui.controller;

import com.senai.CadastroEstacionamento.aplication.dtos.CarroDto;
import com.senai.CadastroEstacionamento.aplication.dtos.EstacionamentoDto;
import com.senai.CadastroEstacionamento.aplication.dtos.UsuarioDto;
import com.senai.CadastroEstacionamento.aplication.service.CarroService;
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

@Tag(name = "Carros", description = "Gerencia os carros do sistema")
@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    CarroService carroService;

    @Operation(
            summary = "Cadastrar um novo carro",
            description = "Adiciona um novo carro à base de dados após validações",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioDto.class),
                            examples = @ExampleObject(value = """
                                        {
                                          "placa": "ABC1234",
                                          "modelo": "Fusca",
                                          "cor": "Azul"
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
    public ResponseEntity<Void> cadastrarCarro(@RequestBody List<CarroDto> dtos){
        carroService.cadastrarCarros(dtos);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Buscar carro por ID",
            description = "Retorna um carro existente a partir do seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Carro encontrado"),
                    @ApiResponse(responseCode = "404", description = "Carro não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CarroDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(CarroDto.toDto(carroService.buscarPorId(id)));

    }
    @Operation(
            summary = "Listar todos os carros",
            description = "Retorna todos os carros cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<CarroDto>> listarCarros(){
        return ResponseEntity.ok(carroService.listarCarros());
    }
    @Operation(
            summary = "Atualizar um carro",
            description = "Atualiza os dados de um carro existente com novas informações",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Carro atualizado"),
                    @ApiResponse(responseCode = "400", description = "Violação de regras de negócio")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody CarroDto dto) {
        if (carroService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(
            summary = "Deletar um carro",
            description = "Remove um carro da base de dados a partir do seu ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Carro removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Carro não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (carroService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
