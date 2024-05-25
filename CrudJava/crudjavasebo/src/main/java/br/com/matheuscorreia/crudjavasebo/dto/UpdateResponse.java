package br.com.matheuscorreia.crudjavasebo.dto;

import br.com.matheuscorreia.crudjavasebo.model.Livro;

public class UpdateResponse {
    private String message;
    private Livro livro;

    public UpdateResponse(String message, Livro livro) {
        this.message = message;
        this.livro = livro;
    }

    public String getMessage() {
        return message;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
