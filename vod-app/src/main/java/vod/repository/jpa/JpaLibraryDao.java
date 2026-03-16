package vod.repository.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.List;

@Repository
public class JpaLibraryDao implements LibraryDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Library> findAll(){
        return entityManager
                .createQuery("select c from Library c")
                .getResultList();
    }
    @Override
    public Library findById(int id) {return entityManager.find(Library.class, id);}

    @Override
    public List<Library> findByBook(Book book){
        return entityManager
                .createQuery("select c from Library c inner join c.books book where book=:book")
                .setParameter("book",book)
                .getResultList();
    }
    @Override
    public Library save(Library library) {
        entityManager.persist(library);
        return library;
    }
}
