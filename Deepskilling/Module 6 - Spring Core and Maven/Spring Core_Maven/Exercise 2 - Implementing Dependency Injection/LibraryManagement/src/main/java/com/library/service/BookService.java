package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService() {}

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title) {
        System.out.println("BookService: Processing addition of book '" + title + "'...");
        if (bookRepository != null) {
            bookRepository.saveBook(title);
        } else {
            System.out.println("Error: BookRepository dependency is missing.");
        }
    }
}
