package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.annotation.Identifier;
import io.smallrye.common.constraint.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;
    private String poster;//url

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;//relacja do rezysera - kolejny obiekt danych w uproszczeniu założenie że jeden film ma 1 reżysera
    private float rating;//rating

    @ManyToMany
    @JoinTable(name="book_library",joinColumns=@JoinColumn(name="book_id",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="library_id",referencedColumnName="id"))
    private List<Library> librarys = new ArrayList<>();
//relacja wiele do wiele - bidirectional

    public Book(int id, String title, String poster, Author author, float rating) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.author = author;
        this.rating = rating;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author Author) {
        this.author = Author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Library> getLibrarys() {
        return librarys;
    }

    public void setLibrarys(List<Library> librarys) {
        this.librarys = librarys;
    }

    public void addLibrary(Library c) {
        this.librarys.add(c);
    }


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book Book = (Book) o;

        if (id != Book.id) return false;
        if (Float.compare(Book.rating, rating) != 0) return false;
        if (title != null ? !title.equals(Book.title) : Book.title != null) return false;
        return poster != null ? poster.equals(Book.poster) : Book.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", Author=" + author +
                ", rating=" + rating +
                '}';
    }
}
