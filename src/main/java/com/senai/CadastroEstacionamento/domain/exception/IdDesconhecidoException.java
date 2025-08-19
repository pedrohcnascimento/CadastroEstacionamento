package com.senai.CadastroEstacionamento.domain.exception;

public class IdDesconhecidoException extends RuntimeException{
    public IdDesconhecidoException(String message) {
        super(message);
    }
}
