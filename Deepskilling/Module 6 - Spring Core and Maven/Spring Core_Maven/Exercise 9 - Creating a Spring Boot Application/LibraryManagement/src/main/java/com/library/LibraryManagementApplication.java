package com.library;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("Spring in Action", "Craig Walls", "9781617294945", 45.99));
            bookRepository.save(new Book("Clean Code", "Robert C. Martin", "9780132350884", 39.95));
            System.out.println("Initial sample books inserted into H2 Database.");
        };
    }
}
