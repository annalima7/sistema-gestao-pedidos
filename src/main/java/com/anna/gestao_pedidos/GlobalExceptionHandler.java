package com.anna.gestao_pedidos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErroResposta> tratarErroValidacao(
        MethodArgumentNotValidException ex) {

    Map<String, String> erros = new HashMap<>();

    for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
        erros.put(erro.getField(), erro.getDefaultMessage());
    }

    ErroResposta resposta = new ErroResposta(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validacao nos campos informados",
            erros
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
}
}