package com.booklibrary;

import java.time.LocalDateTime;

public class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String author;
    private LocalDateTime addedDate;

    public Book(String title, String author) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.addedDate = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public LocalDateTime getAddedDate() { return addedDate; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Added: " + addedDate;
    }
}
