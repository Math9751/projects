package br.com.matheuscorreia.crudjavasebo.exception;

public class LivroNotFoundException extends RuntimeException {
    public LivroNotFoundException(String message) {
        super(message);
    }
}
