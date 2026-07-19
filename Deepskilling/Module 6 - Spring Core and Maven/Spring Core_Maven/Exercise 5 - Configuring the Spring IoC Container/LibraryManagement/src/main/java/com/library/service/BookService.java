package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void serveLibrary() {
        System.out.println("BookService: IoC Container managed service bean executing...");
        if (bookRepository != null) {
            bookRepository.printRepositoryStatus();
        } else {
            System.out.println("BookRepository is null!");
        }
    }
}
