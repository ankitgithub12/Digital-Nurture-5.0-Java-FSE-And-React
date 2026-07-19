package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve BookService bean
        BookService bookService = (BookService) context.getBean("bookService");

        // Execute service method to verify dependency injection
        bookService.addBook("Design Patterns in Java");
    }
}
