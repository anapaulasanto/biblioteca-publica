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

            //EXCEÇÕES PARA CAMPO DUPLICADO
            if (messageLower.contains("(login)=") && messageLower.contains("viola a restrição de unicidade")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Login já existente na base.",
                                "unichristus.user.save.login.unique"));

            } else if (messageLower.contains("(isbn)=") && messageLower.contains("viola a restrição de unicidade")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Livro já existente para o ISBN informado.",
                                "unichristus.book.save.isbn.unique"));

            } else if (messageLower.contains("(category_code)=") && messageLower.contains("viola a restrição de unicidade")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Categoria já existente para o código informado.",
                                "unichristus.category.save.categoryCode.unique"));


            // EXCEÇÕES PARA CAMPO NULO
            } else if (messageLower.contains("not-null property") && messageLower.contains("user.name")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Erro ao tentar salvar usuário. Nome é um campo obrigatório",
                                "unichristus.user.salve.name.not-null"));

            } else if (messageLower.contains("not-null property") && messageLower.contains("book.title")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Erro ao tentar salvar livro. Título é um campo obrigatório",
                                "unichristus.book.salve.title.not-null"));

            } else if (messageLower.contains("not-null property") && messageLower.contains("rental.rentaldate")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Erro ao tentar salvar aluguel. 'rentalDate' é um campo obrigatório",
                                "unichristus.rental.salve.rentalDate.not-null"));

            } else if (messageLower.contains("not-null property") && messageLower.contains("category.categoryname")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Erro ao tentar salvar categoria. 'categoryName' é um campo obrigatório",
                                "unichristus.category.salve.categoryName.not-null"));

            } else if (messageLower.contains("not-null property") && messageLower.contains("review.rating")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageDTO("Erro ao tentar salvar avaliação. 'rating' é um campo obrigatório",
                                "unichristus.category.salve.rating.not-null"));
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST) // exceção genérica
                .body(new MessageDTO("Erro ao tentar salvar. Verifique os dados informados.",
                        "unichristus.global.save.error"));
    }
}

