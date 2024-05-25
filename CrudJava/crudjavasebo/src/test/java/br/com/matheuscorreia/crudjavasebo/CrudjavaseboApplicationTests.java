// package br.com.matheuscorreia.crudjavasebo;

// // import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import static org.springframework.http.MediaType.APPLICATION_JSON;
// import org.springframework.test.web.reactive.server.WebTestClient;
// // import org.springframework.web.reactive.function.BodyInserters;

// import br.com.matheuscorreia.crudjavasebo.model.Livro;
// import br.com.matheuscorreia.crudjavasebo.service.LivroService;

// @WebFluxTest
// public class CrudjavaseboApplicationTests {

//     @Autowired
//     private WebTestClient webTestClient;

//     @MockBean
//     private LivroService livroService;

//     @BeforeEach
//     @Test
// void testCreateLivroSuccess() {
//     Livro livro = new Livro();
//     livro.setIsbn("9788563834010");
//     livro.setTitulo("Para Alem da Biblia");
//     livro.setSinopse("Historia do Antigo Israel");
//     livro.setAutor("Mario Liverani");
//     livro.setPreco(92.15);
//     livro.setEmEstoque(true);

//     // Remova a configuração do retorno fixo e deixe o método create persistir o livro no banco de dados
//     when(livroService.create(any(Livro.class))).thenAnswer(invocation -> {
//         Livro livroPersistido = invocation.getArgument(0);
//         livroPersistido.setId(1L); // Apenas para simular o ID gerado automaticamente
//         return livroPersistido;
//     });

//     webTestClient.post()
//         .uri("/api/livros/cadastro")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(livro)
//         .exchange()
//         .expectStatus().isCreated()
//         .expectBody()
//         .jsonPath("$.isbn").isEqualTo("9788563834010")
//         .jsonPath("$.titulo").isEqualTo("Para Alem da Biblia")
//         .jsonPath("$.sinopse").isEqualTo("Historia do Antigo Israel")
//         .jsonPath("$.autor").isEqualTo("Mario Liverani")
//         .jsonPath("$.preco").isEqualTo(92.15)
//         .jsonPath("$.emEstoque").isEqualTo(true);
// }


//     @Test
//     void testCreateLivroFailure() {
//         var invalidLivro = new Livro(null, "", "", "", 0.0, false);

//         webTestClient.post()
//             .uri("/api/livros/cadastro")
//             .contentType(APPLICATION_JSON)
//             .bodyValue(invalidLivro)
//             .exchange()
//             .expectStatus().isBadRequest();
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

//     webTestClient.get().uri("/api/livros/{id}", 1L)
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody()
//         .jsonPath("$.titulo").isEqualTo("Para Alem da Biblia")
//         .jsonPath("$.isbn").isEqualTo("9788563834010");
// }


// //     @Test
// // public void testUpdateLivroSuccess() throws Exception {
// //     Livro livro = new Livro();
// //     livro.setIsbn("9788563834010");
// //     livro.setTitulo("c");
// //     livro.setSinopse("Historia do Antigo Israel");
// //     livro.setAutor("Mario Liverani");
// //     livro.setPreco(104.99);
// //     livro.setEmEstoque(true);

// //     webTestClient.post().uri("/api/livros/editar")
// //         .contentType(MediaType.APPLICATION_JSON)
// //         .body(BodyInserters.fromValue(livro))
// //         .exchange()
// //         .expectStatus().isCreated()
// //         .expectBody()
// //         .jsonPath("$.id").isNotEmpty();
// // }

//     @Test
//     public void testUpdateLivroFailure() {
//         var invalidLivro = new Livro("", "", "", null, 0.0, false);
    
//         webTestClient
//                 .put()
//                 .uri("/api/livros/editar/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .bodyValue(invalidLivro)
//                 .exchange()
//                 .expectStatus().isNotFound();
//     }    

//     // @SuppressWarnings("null")
//     // @SuppressWarnings("null")
//     // @Test
//     // public void testDeleteLivroSuccess() {
//     //     Livro livro = new Livro();
//     //     livro.setIsbn("9788563834010");
//     //     livro.setTitulo("Para Alem da Biblia");
//     //     livro.setSinopse("Historia do Antigo Israel");
//     //     livro.setAutor("Mario Liverani");
//     //     livro.setPreco(92.15);
//     //     livro.setEmEstoque(true);
    
//     //     // Cria o livro
//     //     Livro createdLivro = webTestClient
//     //             .post()
//     //             .uri("/api/livros/cadastro")
//     //             .contentType(MediaType.APPLICATION_JSON)
//     //             .bodyValue(livro)
//     //             .exchange()
//     //             .expectStatus().isCreated()
//     //             .expectBody(Livro.class)
//     //             .returnResult()
//     //             .getResponseBody();
    
//     //     // Obtém o ID do livro criado
//     //     Long livroId = createdLivro.getId();
    
//     //     // Deleta o livro usando o ID obtido
//     //     webTestClient
//     //             .delete()
//     //             .uri("/api/livros/excluir/" + livroId)
//     //             .exchange()
//     //             .expectStatus().isNoContent();
//     // }
    

    

//     // @Test
//     // public void testDeleteLivroFailure() {
//     //     var unexinstingId = 1L;

//     //     webTestClient
//     //             .delete()
//     //             .uri("/api/livros/excluir/" + unexinstingId)
//     //             .exchange()
//     //             .expectStatus().isNotFound();
//     // }

// }
