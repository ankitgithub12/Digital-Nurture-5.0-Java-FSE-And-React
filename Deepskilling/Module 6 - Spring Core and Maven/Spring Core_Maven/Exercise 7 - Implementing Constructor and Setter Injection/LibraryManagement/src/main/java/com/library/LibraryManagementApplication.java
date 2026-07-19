package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        System.out.println("Loading Spring Context for Constructor and Setter Injection Test...");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n--- Testing Constructor Injection ---");
        BookService service1 = (BookService) context.getBean("bookServiceConstructor");
        service1.performService();

        System.out.println("\n--- Testing Setter Injection ---");
        BookService service2 = (BookService) context.getBean("bookServiceSetter");
        service2.performService();
    }
}
