package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AuthorId;
    private String Name;
    private String Country;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.REMOVE)
    private List<Book> bookList;

    public Author(int authorId, String name, String country, List<Book> bookList) {
        AuthorId = authorId;
        Name = name;
        Country = country;
        this.bookList = bookList;
    }

    public Author() {
    }

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "AuthorId=" + AuthorId +
                ", Name='" + Name + '\'' +
                ", Country='" + Country + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
