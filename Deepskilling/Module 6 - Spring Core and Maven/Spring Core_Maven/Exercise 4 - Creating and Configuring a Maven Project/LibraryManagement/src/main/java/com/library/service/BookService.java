package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processLibraryService() {
        System.out.println("LibraryManagement: Book Service active.");
        if (bookRepository != null) {
            bookRepository.getRepositoryDetails();
        }
    }
}
