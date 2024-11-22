package com.example.helloworld.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    void shouldSetAndGetId() {
        Book book = new Book();
        book.setId(1L);
        assertThat(book.getId()).isEqualTo(1L);
    }

    @Test
    void shouldSetAndGetTitle() {
        Book book = new Book();
        book.setTitle("Test Title");
        assertThat(book.getTitle()).isEqualTo("Test Title");
    }

    @Test
    void shouldSetAndGetAuthor() {
        Book book = new Book();
        book.setAuthor("Test Author");
        assertThat(book.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    void shouldVerifyEquality() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test Title");
        book1.setAuthor("Test Author");

        Book book2 = new Book();
        book2.setId(1L);
        book2.setTitle("Test Title");
        book2.setAuthor("Test Author");

        assertThat(book1).isEqualTo(book2);
    }

    @Test
    void shouldVerifyHashCode() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test Title");
        book1.setAuthor("Test Author");

        Book book2 = new Book();
        book2.setId(1L);
        book2.setTitle("Test Title");
        book2.setAuthor("Test Author");

        assertThat(book1.hashCode()).isEqualTo(book2.hashCode());
    }
}