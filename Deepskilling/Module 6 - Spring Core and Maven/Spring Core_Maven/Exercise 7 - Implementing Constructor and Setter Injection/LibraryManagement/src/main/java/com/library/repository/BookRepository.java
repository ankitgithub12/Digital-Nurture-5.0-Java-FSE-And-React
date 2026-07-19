package com.library.repository;

public class BookRepository {
    private String repoName;

    public BookRepository() {
        this.repoName = "Default Library Repository";
    }

    public BookRepository(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void displayInfo() {
        System.out.println("BookRepository [" + repoName + "]: Operational.");
    }
}
