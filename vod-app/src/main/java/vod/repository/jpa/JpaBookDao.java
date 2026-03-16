package vod.repository.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Book;
import vod.repository.BookDao;

import java.util.List;

@Repository
@Primary
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
                "select book from Book book inner join book.libraries library where library=:library"
        ).setParameter("library",l)
                .getResultList();
    }

    @Override
    public Book add(Book book) {
        entityManager.persist(book);
        return book;
    }
}
