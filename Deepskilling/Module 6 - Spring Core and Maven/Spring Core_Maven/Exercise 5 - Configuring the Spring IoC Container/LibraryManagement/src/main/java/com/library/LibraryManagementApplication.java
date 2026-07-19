package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring IoC Container configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve bean managed by IoC container
        BookService bookService = (BookService) context.getBean("bookService");

        // Test the configuration and IoC dependency resolution
        bookService.serveLibrary();
    }
}
