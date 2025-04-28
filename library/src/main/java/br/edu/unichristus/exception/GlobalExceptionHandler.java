package br.edu.unichristus.exception;

import br.edu.unichristus.domain.dto.MessageDTO;
import org.hibernate.engine.spi.ExceptionConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CommonsException.class)
    protected ResponseEntity<MessageDTO> handleCommonsException(CommonsException exception) {
        logger.error("[exception] " + exception);
        return exception.getMessageError();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageDTO> handleException(Exception ex) {
        logger.error("Exception não tratada: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageDTO("Exception não tratada: " + ex.toString(),
                        "unichristus.global.handler.exception"));
    }

    // Trata exceções que violam regras do banco (ex login repetido)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<MessageDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        logger.error("Erro de integridade no banco de dados: ", ex);
        String message = ex.getMostSpecificCause().getMessage();

            String messageLower = message.toLowerCase();

            if (messageLower.contains("(login)=") && messageLower.contains("viola a restrição de unicidade")) { // exceção de user com login já existente
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Já existe um usuário cadastrado com esse login.",
                                "unichristus.user.save.login.unique"));

            } else if (messageLower.contains("(isbn)=") && messageLower.contains("viola a restrição de unicidade")) { // exceção de book com isbn já existente
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Já existe um livro cadastrado com esse isbn.",
                                "unichristus.book.save.isbn.unique"));

            } else if (messageLower.contains("(category_code)=") && messageLower.contains("viola a restrição de unicidade")) {// exceção de category com categoryCode já existente
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Já existe uma categoria cadastrada com esse código.",
                                "unichristus.category.save.categoryCode.unique"));
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST) // exceção genérica
                .body(new MessageDTO("Erro ao tentar salvar. Verifique os dados informados.",
                        "unichristus.user.global.save.error"));
    }
}

