package com.unicalsocial.backend.handler;


import com.unicalsocial.backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CantFollowSameUserException.class)
    public ResponseEntity<ExceptionResponse> handleException(CantFollowSameUserException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_REQUEST.value())
                        .businessErrorDescription(exp.toString())
                        .error("Non puoi auto seguirti")
                        .build()
        );
    }

    @ExceptionHandler(CantFollowTwoTimeSameUser.class)
    public ResponseEntity<ExceptionResponse> handleException(CantFollowTwoTimeSameUser exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_REQUEST.value())
                        .businessErrorDescription(exp.toString())
                        .error("Non puoi mettere seguire due volte lo stesso utente")
                        .build()
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserNotFoundException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(NOT_FOUND.value())
                        .businessErrorDescription(exp.toString())
                        .error("Utente non trovato")
                        .build()
        );
    }

    @ExceptionHandler(FollowerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(FollowerNotFoundException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(NOT_FOUND.value())
                        .businessErrorDescription(exp.toString())
                        .error("Follower non trovato")
                        .build()
        );
    }

    @ExceptionHandler(MipiaceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(MipiaceNotFoundException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(NOT_FOUND.value())
                        .businessErrorDescription(exp.toString())
                        .error("Mi piace non trovato")
                        .build()
        );
    }

    @ExceptionHandler(PostMediaNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(PostMediaNotFoundException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(NOT_FOUND.value())
                        .businessErrorDescription(exp.toString())
                        .error("Post media non trovato")
                        .build()
        );
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(PostNotFoundException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(NOT_FOUND.value())
                        .businessErrorDescription(exp.toString())
                        .error("Post non trovato")
                        .build()
        );
    }

    @ExceptionHandler(CantDeletePostOfOtherUserException.class)
    public ResponseEntity<ExceptionResponse> handleException(CantDeletePostOfOtherUserException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_REQUEST.value())
                        .businessErrorDescription(exp.toString())
                        .error("Non puoi cancellare i post degli altri utenti")
                        .build()
        );
    }

    @ExceptionHandler(UserNotInitializedException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserNotInitializedException exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(INTERNAL_SERVER_ERROR.value())
                                .businessErrorDescription("L'utente non è stato inizializzato")
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(UserCantLikeTwoTimeSamePost.class)
    public ResponseEntity<ExceptionResponse> handleException(UserCantLikeTwoTimeSamePost exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_REQUEST.value())
                                .businessErrorDescription("Non puoi mettere like due volte allo stesso post")
                                .error(exp.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(CommentNotFoundException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(NOT_FOUND.value())
                        .businessErrorDescription(exp.toString())
                        .error("Commento non trovato")
                        .build()
        );
    }

    @ExceptionHandler(CantDeleteCommentOfOtherUserException.class)
    public ResponseEntity<ExceptionResponse> handleException(CantDeleteCommentOfOtherUserException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_REQUEST.value())
                                .businessErrorDescription("Non puoi eliminare il commento di un altro utente")
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(INTERNAL_SERVER_ERROR.value())
                                .businessErrorDescription("Internal error")
                                .error(exp.getMessage())
                                .build()
                );
    }
}
