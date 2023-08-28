package application.app.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String author;
    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Integer launch_date;
    private Double price;
    private String title;
    @Column(length = 13)
    private Long isbn;

    public Book() {
    }

    public Book(Long id, String author, Integer launch_date, Double price, String title, Long isbn) {
        this.id = id;
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.title = title;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Integer launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
}
