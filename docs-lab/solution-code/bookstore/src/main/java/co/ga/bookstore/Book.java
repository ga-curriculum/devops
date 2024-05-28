package co.ga.bookstore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Book entity to represent books in our data model. A book has a title. We'll be able to search for books by title in {@link BookRepository}.
 *
 * @author alex
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;

    /**
     * The default constructor.
     */
    public Book() {
    }

    /**
     * Constructor with title as an argument
     * @param title the title for this book
     */
    public Book(String title) {
        this.title = title;
    }

    /**
     * Gets the ID for this book.
     * @return the auto-generated ID for this book
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID for this book.
     * @param id the ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of this book.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for this book
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Overrides the default implementation of {@link Object#toString()} to return a prettier display of this books title and ID.
     * @return a String containing this Book's ID and title
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}