package com.booklibrary;

import java.time.LocalDateTime;

public class Book {
    private int id;
    private String title;
    private String author;
    private LocalDateTime addedDate;

    public Book(String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.addedDate = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public LocalDateTime getAddedDate() { return addedDate; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }

    public void setTitle(String s) {
    }

    public void setAuthor(String s) {

    }
}
