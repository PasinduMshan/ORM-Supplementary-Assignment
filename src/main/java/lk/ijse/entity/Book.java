package lk.ijse.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private int public_year;
    private double price;

    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private Author author;


    public Book(int bookId, String title, int public_year, double price, Author author) {
        this.bookId = bookId;
        this.title = title;
        this.public_year = public_year;
        this.price = price;
        this.author = author;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublic_year() {
        return public_year;
    }

    public void setPublic_year(int public_year) {
        this.public_year = public_year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
