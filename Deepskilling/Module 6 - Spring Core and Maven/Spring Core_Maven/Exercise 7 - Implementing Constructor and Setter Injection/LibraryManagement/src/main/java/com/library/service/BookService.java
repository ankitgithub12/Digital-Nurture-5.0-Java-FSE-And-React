package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;
    private String serviceName;

    // Default Constructor
    public BookService() {}

    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService initialized via Constructor Injection!");
    }

    // Setter Injection for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected into BookService via Setter Injection!");
    }

    // Setter Injection for Service Name
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void performService() {
        System.out.println("BookService [" + serviceName + "]: Performing library operations...");
        if (bookRepository != null) {
            bookRepository.displayInfo();
        } else {
            System.out.println("BookRepository is null.");
        }
    }
}
