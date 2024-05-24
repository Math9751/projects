package br.com.matheuscorreia.crudjavasebo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.matheuscorreia.crudjavasebo.model.Livro;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CrudjavaseboApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateLivroSuccess() {
        var livro = new Livro(null, "9788563834010", "Para Além da Bíblia", 92.15, true);

        webTestClient
                .post()
                .uri("/api/livros/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(livro)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.isbn").isEqualTo(livro.getIsbn())
                .jsonPath("$.titulo").isEqualTo(livro.getTitulo())
                .jsonPath("$.sinopse").isEqualTo(livro.getSinopse())
                .jsonPath("$.autor").isEqualTo(livro.getAutor())
                .jsonPath("$.preco").isEqualTo(livro.getPreco())
                .jsonPath("$.emEstoque").isEqualTo(livro.isEmEstoque());
    }

    @Test
    void testCreateLivroFailure() {
        var invalidLivro = new Livro(null, "", "", 0, false);

        webTestClient
                .post()
                .uri("/api/livros/")
                .bodyValue(invalidLivro)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testUpdateLivroSuccess() {
        var livro = new Livro(null, "9788563834010", "Para Além da Bíblia", 92.15, true);
        Livro createdLivro = webTestClient
                .post()
                .uri("/api/livros/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(livro)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Livro.class)
                .returnResult()
                .getResponseBody();

        createdLivro.setTitulo("Novo Título");
        createdLivro.setSinopse("Nova Sinopse");

        webTestClient
                .put()
                .uri("/api/livros/" + createdLivro.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createdLivro)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.titulo").isEqualTo("Novo Título")
                .jsonPath("$.sinopse").isEqualTo("Nova Sinopse");
    }

    @Test
    public void testUpdateLivroFailure() {
        var invalidLivro = new Livro("", "", "", 0, false);

        webTestClient
                .put()
                .uri("/api/livros/1")
                .bodyValue(invalidLivro)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testDeleteLivroSuccess() {
        var livro = new Livro(null, "9788563834010", "Para Além da Bíblia", 92.15, true);
        Livro createdLivro = webTestClient
                .post()
                .uri("/api/livros/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(livro)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Livro.class)
                .returnResult()
                .getResponseBody();

        webTestClient
                .delete()
                .uri("/api/livros/" + createdLivro.getId())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testDeleteLivroFailure() {
        var unexinstingId = 1L;

        webTestClient
                .delete()
                .uri("/api/livros/" + unexinstingId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testListLivros() {
        webTestClient
                .get()
                .uri("/api/livros")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray();
    }
}
