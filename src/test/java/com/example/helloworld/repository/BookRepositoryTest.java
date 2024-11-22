package com.example.helloworld.repository;

import com.example.helloworld.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BookRepository.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        // Create a table for testing
        jdbcTemplate.execute("""
            CREATE TABLE book (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(255) NOT NULL,
                author VARCHAR(255) NOT NULL
            )
        """);
    }

    @Test
    void shouldSaveAndRetrieveBook() {
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");

        Book savedBook = bookRepository.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("1984");
        assertThat(savedBook.getAuthor()).isEqualTo("George Orwell");
    }

    @Test
    void shouldFindAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setAuthor("Author One");

        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setAuthor("Author Two");

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findAll();

        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle).contains("Book One", "Book Two");
    }

    @Test
    void shouldFindBookById() {
        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");

        Book savedBook = bookRepository.save(book);

        Book retrievedBook = bookRepository.findById(savedBook.getId());

        assertThat(retrievedBook).isNotNull();
        assertThat(retrievedBook.getTitle()).isEqualTo("The Great Gatsby");
    }

    @Test
    void shouldUpdateBook() {
        Book book = new Book();
        book.setTitle("Old Title");
        book.setAuthor("Old Author");

        Book savedBook = bookRepository.save(book);

        savedBook.setTitle("New Title");
        savedBook.setAuthor("New Author");

        bookRepository.update(savedBook.getId(), savedBook);

        Book updatedBook = bookRepository.findById(savedBook.getId());

        assertThat(updatedBook.getTitle()).isEqualTo("New Title");
        assertThat(updatedBook.getAuthor()).isEqualTo("New Author");
    }

    @Test
    void shouldDeleteBook() {
        Book book = new Book();
        book.setTitle("To Be Deleted");
        book.setAuthor("Author");

        Book savedBook = bookRepository.save(book);

        bookRepository.delete(savedBook.getId());

        List<Book> books = bookRepository.findAll();

        assertThat(books).isEmpty();
    }
}