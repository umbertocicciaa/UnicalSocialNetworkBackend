package com.unicalsocial.backend.handler;


import com.unicalsocial.backend.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CantFollowSameUserException.class)
    public ResponseEntity<ExceptionResponse> handleException(CantFollowSameUserException exp) {
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

    @ExceptionHandler(UserHasLikedPostException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserHasLikedPostException exp) {
        return ResponseEntity.badRequest().body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_REQUEST.value())
                        .businessErrorDescription(exp.toString())
                        .error("Non puoi mettere like due volte allo stesso utente")
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
