package ru.hazov.booksdemo.controller.book_controller.methods.searchBooks;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import ru.hazov.booksdemo.controller.BookController;
import ru.hazov.booksdemo.dto.books.search_books.request.BookFilterRequest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;
import ru.hazov.booksdemo.service.auth.JwtAuthFilter;
import ru.hazov.booksdemo.service.auth.PersonDetailsService;
import ru.hazov.booksdemo.service.auth.TokenService;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class SearchBooksTests {

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
    @DisplayName("searchBooks method test")
    public void given_whenSearchBooks_then() throws Exception {
        // given
        BookFilterRequest request = SearchBookTestUtils.validBookFilterRequest();
        List<Book> persistedBooks = SearchBookTestUtils.persistedBooks();
        BDDMockito.given(bookService.searchBooks(any())).willReturn(persistedBooks);
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        Map<String, String> fieldMap = objectMapper.convertValue(request, new TypeReference<>() {});
        valueMap.setAll(fieldMap);
        // when
        ResultActions resultActions = mvc.perform(get("/api/v1/books/search")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParams(valueMap)
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
