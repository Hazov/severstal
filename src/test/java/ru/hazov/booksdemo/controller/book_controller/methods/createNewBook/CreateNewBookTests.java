package ru.hazov.booksdemo.controller.book_controller.methods.createNewBook;

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
import ru.hazov.booksdemo.controller.BookController;
import ru.hazov.booksdemo.dto.books.create_book.CreateBookRequest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;
import ru.hazov.booksdemo.service.auth.JwtAuthFilter;
import ru.hazov.booksdemo.service.auth.PersonDetailsService;
import ru.hazov.booksdemo.service.auth.TokenService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class CreateNewBookTests {

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
    @DisplayName("createNewBook method test")
    public void givenValidCreateBookRequest_whenCreateNewBook_thenOk() throws Exception {
        // given
        CreateBookRequest createBookRequest = CreateNewBookTestUtils.validCreateBookRequest();
        Book createdBook = CreateNewBookTestUtils.createdBook();
        BDDMockito.given(bookService.createNewBook(any())).willReturn(createdBook);
        // when
        ResultActions resultActions = mvc.perform(post("/api/v1/books/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBookRequest))
        );
        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(createdBook.getId().intValue())))
                .andExpect(jsonPath("$.title", CoreMatchers.is(createdBook.getTitle())))
                .andExpect(jsonPath("$.author", CoreMatchers.is(createdBook.getAuthor())));
    }
}
