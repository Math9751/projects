package br.com.matheuscorreia.crudjavasebo;

// import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.matheuscorreia.crudjavasebo.model.Livro;
import br.com.matheuscorreia.crudjavasebo.service.LivroService;

@WebFluxTest
public class CrudjavaseboApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LivroService livroService;

    @Test
void testCreateLivroSuccess() {
    Livro livro = new Livro("1234567890123L", "Titulo Teste", "Sinopse Teste", "Autor Teste", 19.99, true);

    when(livroService.create(livro)).thenReturn(livro);

    webTestClient.post()
        .uri("/api/livros")
        .contentType(APPLICATION_JSON)
        .bodyValue(livro)
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        .jsonPath("$.isbn").isEqualTo("1234567890123L")
        .jsonPath("$.titulo").isEqualTo("Titulo Teste")
        .jsonPath("$.sinopse").isEqualTo("Sinopse Teste")
        .jsonPath("$.autor").isEqualTo("Autor Teste")
        .jsonPath("$.preco").isEqualTo(19.99)
        .jsonPath("$.emEstoque").isEqualTo(true);
}


    @Test
    void testCreateLivroFailure() {
        var invalidLivro = new Livro(null, "", "", "", 0.0, false);

        webTestClient.post()
            .uri("/api/livros")
            .contentType(APPLICATION_JSON)
            .bodyValue(invalidLivro)
            .exchange()
            .expectStatus().isBadRequest();
    }
    

    // @Test
    // public void testUpdateLivroSuccess() {
    //     var livro = new Livro("9788563834010", "Para Além da Bíblia", "História do Antigo Israel", "Mário Liverani", 92.15, true);
    //     Livro createdLivro = webTestClient
    //             .post()
    //             .uri("/api/livros")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .bodyValue(livro)
    //             .exchange()
    //             .expectStatus().isCreated()
    //             .expectBody(Livro.class)
    //             .returnResult()
    //             .getResponseBody();
    
    //     createdLivro.setTitulo("Novo Título");
    //     createdLivro.setSinopse("Nova Sinopse");
    
    //     webTestClient
    //             .put()
    //             .uri("/api/livros/" + createdLivro.getId())
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .bodyValue(createdLivro)
    //             .exchange()
    //             .expectStatus().isOk()
    //             .expectBody()
    //             .jsonPath("$.titulo").isEqualTo("Novo Título")
    //             .jsonPath("$.sinopse").isEqualTo("Nova Sinopse");
    // }
    

    // @Test
    // public void testUpdateLivroFailure() {
    //     var invalidLivro = new Livro("", "", "", null, 0, false);
    
    //     webTestClient
    //             .put()
    //             .uri("/api/livros/1")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .bodyValue(invalidLivro)
    //             .exchange()
    //             .expectStatus().isNotFound();
    // }    
}
