package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void manageBooks() {
        System.out.println("BookService: Managing library books...");
        if (bookRepository != null) {
            bookRepository.displayRepositoryInfo();
        } else {
            System.out.println("BookRepository is not configured!");
        }
    }
}
