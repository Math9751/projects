// package br.com.matheuscorreia.crudjavasebo;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.mockito.ArgumentMatchers.any;
// import org.mockito.InjectMocks;
// import static org.mockito.Mockito.when;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import static org.springframework.http.MediaType.APPLICATION_JSON;
// import org.springframework.test.web.reactive.server.WebTestClient;
// import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;
// import org.springframework.web.reactive.function.BodyInserters;

// import br.com.matheuscorreia.crudjavasebo.controller.LivroController;
// import br.com.matheuscorreia.crudjavasebo.model.Livro;
// import br.com.matheuscorreia.crudjavasebo.service.LivroService;

// @WebFluxTest
// public class CrudjavaseboApplicationTests {

//     @Autowired
//     private WebTestClient webTestClient;

//     @InjectMocks
//     private LivroController livroController;


//     @MockBean
//     private LivroService livroService;

//     @Test
// void testCreateLivroSuccess() {
//     Livro livro = new Livro();
//     livro.setIsbn("123456789");
//     livro.setTitulo("Não Sei");
//     livro.setSinopse("Blablabla");
//     livro.setAutor("Fulano Sicrano");
//     livro.setPreco(10.99);
//     livro.setEmEstoque(false);

//     // Remova a configuração do retorno fixo e deixe o método create persistir o livro no banco de dados
//     when(livroService.create(any(Livro.class))).thenAnswer(invocation -> {
//         Livro livroPersistido = invocation.getArgument(0);
//         livroPersistido.setId(1L); // Apenas para simular o ID gerado automaticamente
//         return livroPersistido;
//     });

//     webTestClient
//         .post()
//         .uri("/api/livros")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(livro)
//         .exchange()
//         .expectStatus().isCreated()
//         .expectBody()
//         .jsonPath("$.isbn").isEqualTo(livro.getIsbn())
//         .jsonPath("$.titulo").isEqualTo(livro.getTitulo())
//         .jsonPath("$.sinopse").isEqualTo(livro.getSinopse())
//         .jsonPath("$.autor").isEqualTo(livro.getAutor())
//         .jsonPath("$.preco").isEqualTo(livro.getPreco())
//         .jsonPath("$.emEstoque").isEqualTo(livro.isEmEstoque());
// }


//     @Test
//     void testCreateLivroFailure() {
//         var invalidLivro = new Livro(null, "", "", "", 0.0, false);

//         webTestClient
//             .post()
//             .uri("/api/livros")
//             .contentType(APPLICATION_JSON)
//             .bodyValue(invalidLivro)
//             .exchange()
//             .expectStatus().isBadRequest();
//     }

//     @BeforeEach
//     public void setup() {
//         MockitoAnnotations.openMocks(this);
//         webTestClient = bindToController(livroController).build();
//     }

//     @Test
//     public void testBuscarTodosLivros() {
//         Livro livro1 = new Livro();
//         livro1.setId(1L);
//         livro1.setIsbn("9788563834010");
//         livro1.setTitulo("Para Alem da Biblia");
//         livro1.setSinopse("Historia do Antigo Israel");
//         livro1.setAutor("Mario Liverani");
//         livro1.setPreco(92.15);
//         livro1.setEmEstoque(true);

//         Livro livro2 = new Livro();
//         livro2.setId(2L);
//         livro2.setIsbn("9788576570086");
//         livro2.setTitulo("Sapiens: Uma Breve Historia da Humanidade");
//         livro2.setSinopse("Historia da Humanidade");
//         livro2.setAutor("Yuval Noah Harari");
//         livro2.setPreco(49.90);
//         livro2.setEmEstoque(true);

//         List<Livro> livros = Arrays.asList(livro1, livro2);

//         when(livroService.findAll()).thenReturn(livros);

//         webTestClient
//             .get()
//             .uri("/api/livros")
//             .exchange()
//             .expectStatus().isOk()
//             .expectBody()
//             .jsonPath("$[0].titulo").isEqualTo("Para Alem da Biblia")
//             .jsonPath("$[0].isbn").isEqualTo("9788563834010")
//             .jsonPath("$[1].titulo").isEqualTo("Sapiens: Uma Breve Historia da Humanidade")
//             .jsonPath("$[1].isbn").isEqualTo("9788576570086");
//     }


//     @Test
// public void testBuscarLivroPorId() {
//     Livro livro = new Livro();
//     livro.setId(1L);
//     livro.setIsbn("9788563834010");
//     livro.setTitulo("Para Alem da Biblia");
//     livro.setSinopse("Historia do Antigo Israel");
//     livro.setAutor("Mario Liverani");
//     livro.setPreco(92.15);
//     livro.setEmEstoque(true);

//     when(livroService.findById(1L)).thenReturn(Optional.of(livro));

//     webTestClient
//         .get()
//         .uri("/api/livros/{id}", 1L)
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody()
//         .jsonPath("$.titulo").isEqualTo("Para Alem da Biblia")
//         .jsonPath("$.isbn").isEqualTo("9788563834010");
// }


//     @Test
// public void testUpdateLivroSuccess() throws Exception {
//     Livro livro = new Livro();
//     livro.setIsbn("9788563834010");
//     livro.setTitulo("Para Alem da Biblia");
//     livro.setSinopse("Historia do Antigo Israel");
//     livro.setAutor("Mario Liverani");
//     livro.setPreco(104.99);
//     livro.setEmEstoque(true);

//     webTestClient
//         .put()
//         .uri("/api/livros/{id}")
//         .contentType(MediaType.APPLICATION_JSON)
//         .body(BodyInserters.fromValue(livro))
//         .exchange()
//         .expectStatus().isCreated()
//         .expectBody()
//         .jsonPath("$.id").isNotEmpty();
// }

//     @Test
//     public void testUpdateLivroFailure() {
//         var invalidLivro = new Livro("", "", "", null, 0.0, false);
    
//         webTestClient
//                 .put()
//                 .uri("/api/livros/{id}}")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .bodyValue(invalidLivro)
//                 .exchange()
//                 .expectStatus().isNotFound();
//     }    

 
//     @SuppressWarnings("null")
//     @Test
//     public void testDeleteLivroSuccess() {
//         Livro livro = new Livro();
//         livro.setIsbn("9788563834010");
//         livro.setTitulo("Para Alem da Biblia");
//         livro.setSinopse("Historia do Antigo Israel");
//         livro.setAutor("Mario Liverani");
//         livro.setPreco(92.15);
//         livro.setEmEstoque(true);
    
//         // Cria o livro
//         Livro createdLivro = webTestClient
//                 .post()
//                 .uri("/api/livros")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .bodyValue(livro)
//                 .exchange()
//                 .expectStatus().isCreated()
//                 .expectBody(Livro.class)
//                 .returnResult()
//                 .getResponseBody();
    
//         // Obtém o ID do livro criado
//         Long livroId = createdLivro.getId();
    
//         // Deleta o livro usando o ID obtido
//         webTestClient
//                 .delete()
//                 .uri("/api/livros/" + livroId)
//                 .exchange()
//                 .expectStatus().isNoContent();
//     }
    

    

//     @Test
//     public void testDeleteLivroFailure() {
//         var unexistingId = 1L;

//         webTestClient
//                 .delete()
//                 .uri("/api/livros/" + unexistingId)
//                 .exchange()
//                 .expectStatus().isNotFound();
//     }

// }
