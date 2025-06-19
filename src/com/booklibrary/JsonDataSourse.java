package com.booklibrary;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonDataSourse {
    private static final String FILE_NAME = "book.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static void saveBooksToFile(List<Book> books) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooksFromFile() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type bookListType = new TypeToken<List<Book>>() {}.getType();
            List<Book> books = gson.fromJson(reader, bookListType);
            return books != null ? books : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
