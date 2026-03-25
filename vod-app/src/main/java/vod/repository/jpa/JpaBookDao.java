package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.repository.BookDao;

import java.util.List;

@Repository

public class JpaBookDao implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll(){
        return entityManager.createQuery(
                "select book from Book book"
        ).getResultList();
    }

    @Override
    public Book findById(int id) {return entityManager.find(Book.class, id);}

    @Override
    public List<Book> findByAuthor(Author a){
        return entityManager
                .createQuery("select book from Book book where book.author=:author")
                .setParameter("author", a)
                .getResultList();
    }

    @Override
    public List<Book> findByLibrary(Library l){
        return entityManager.createQuery(
                "select book from Book book inner join book.librarys library where library=:library"
        ).setParameter("library",l)
                .getResultList();
    }

    @Override
    public Book add(Book book) {
        entityManager.persist(book);
        return book;
    }
}
