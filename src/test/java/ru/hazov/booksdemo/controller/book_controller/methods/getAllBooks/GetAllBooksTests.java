package ru.hazov.booksdemo.controller.book_controller.methods.getAllBooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.hazov.booksdemo.controller.BookController;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;
import ru.hazov.booksdemo.service.auth.JwtAuthFilter;
import ru.hazov.booksdemo.service.auth.PersonDetailsService;
import ru.hazov.booksdemo.service.auth.TokenService;
import ru.hazov.booksdemo.service.book_service.methods.getAllBooks.GetAllBooksUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class GetAllBooksTests {

    @MockitoBean
    TokenService tokenService;

    @MockitoBean
    JwtAuthFilter jwtAuthFilter;

    @MockitoBean
    PersonDetailsService personDetailsService;

    @MockitoBean
    PersonRepository personRepository;

    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockitoBean
    BookService bookService;



    @Test
    @DisplayName("getAllBooks method test")
    public void whenGetAllBooks_then() throws Exception {
        // given
        List<Book> persistedBooks = GetAllBooksUtils.allBooks();
        given(bookService.getAllBooks())
                .willReturn(CompletableFuture.completedFuture(persistedBooks));
        // when

        ResultActions resultActions = mvc.perform(get("/api/v1/books/all")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books[0].id", CoreMatchers.is(persistedBooks.get(0).getId().intValue())))
                .andExpect(jsonPath("$.books[0].title", CoreMatchers.is(persistedBooks.get(0).getTitle())))
                .andExpect(jsonPath("$.books[0].author", CoreMatchers.is(persistedBooks.get(0).getAuthor())))
                .andExpect(jsonPath("$.books[1].id", CoreMatchers.is(persistedBooks.get(1).getId().intValue())))
                .andExpect(jsonPath("$.books[1].title", CoreMatchers.is(persistedBooks.get(1).getTitle())))
                .andExpect(jsonPath("$.books[1].author", CoreMatchers.is(persistedBooks.get(1).getAuthor())));
    }
}
