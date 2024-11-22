package com.example.helloworld.integration;

import com.example.helloworld.model.Book;
import com.example.helloworld.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        // Clear the database before each test
        bookRepository.findAll().forEach(book -> bookRepository.delete(book.getId()));
    }

    @Test
    void testCreateBook() throws Exception {
        String bookJson = """
                {
                    "title": "The Great Gatsby",
                    "author": "F. Scott Fitzgerald"
                }
                """;

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("The Great Gatsby"))
                .andExpect(jsonPath("$.author").value("F. Scott Fitzgerald"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setAuthor("Author One");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setAuthor("Author Two");
        bookRepository.save(book2);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[1].title").value("Book Two"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book();
        book.setTitle("Single Book");
        book.setAuthor("Single Author");
        book = bookRepository.save(book);

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Single Book"))
                .andExpect(jsonPath("$.author").value("Single Author"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Old Title");
        book.setAuthor("Old Author");
        book = bookRepository.save(book);

        String updatedBookJson = """
                {
                    "title": "New Title",
                    "author": "New Author"
                }
                """;

        mockMvc.perform(put("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.author").value("New Author"));
    }

    @Test
    void testDeleteBook() throws Exception {
        Book book = new Book();
        book.setTitle("Book to Delete");
        book.setAuthor("Author to Delete");
        book = bookRepository.save(book);

        mockMvc.perform(delete("/books/" + book.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isNotFound());
    }
}