package pl.clockworkjava.hotelreservation.jpa;

import java.time.Year;

public class App {
    public static void main(String[] args) {
        BookRepository bookRepo = new BookRepository();
        bookRepo.createNewBook("Tytuł A", "Autor A", Year.of(2000), 10);
    }
}
