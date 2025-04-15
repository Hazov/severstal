package ru.hazov.booksdemo.controller.book_controller.methods.rentBook;

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
import ru.hazov.booksdemo.dto.books.rent_book.RentBookRequest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;
import ru.hazov.booksdemo.service.auth.JwtAuthFilter;
import ru.hazov.booksdemo.service.auth.PersonDetailsService;
import ru.hazov.booksdemo.service.auth.TokenService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class RentBookTests {

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
    @DisplayName("rentBook method test")
    public void given_whenRentBook_then() throws Exception {
        // given

        RentBookRequest request = RentBookTestUtils.validRentBookRequest();
        Book persistedBook = RentBookTestUtils.persistedBook();
        BDDMockito.given(bookService.rentBookById(any(), anyLong())).willReturn(persistedBook);
        // when
        ResultActions resultActions = mvc.perform(put("/api/v1/books/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );
        // then
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId", CoreMatchers.is(1)));

    }


}
