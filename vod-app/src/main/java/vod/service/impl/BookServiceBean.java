package vod.service.impl;

import io.undertow.util.Transfer;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vod.repository.LibraryDao;
import vod.repository.AuthorDao;
import vod.repository.BookDao;
import vod.model.Library;
import vod.model.Author;
import vod.model.Book;
import vod.service.BookService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private final AuthorDao authorDao;
    private final LibraryDao libraryDao;
    private final BookDao bookDao;
    private final PlatformTransactionManager transactionManager;

    /*public BookServiceBean(AuthorDao authorDao, @Qualifier("LibraryDao") LibraryDao libraryDao, BookDao bookDao ){
        this.authorDao = authorDao;
        this.libraryDao = libraryDao;
        this.bookDao = bookDao;

    }*/

    public List<Book> getAllBooks() {
        log.info("searching all Books...");
        return bookDao.findAll();
    }

    public List<Book> getBooksByAuthor(Author d) {
        log.info("serching Books by diretor " + d.getId());
        return bookDao.findByAuthor(d);
    }

    public List<Book> getBooksInLibrary(Library c) {
        log.info("searching Books played in Library " + c.getId());
        return bookDao.findByLibrary(c);
    }

    public Book getBookById(int id) {
        log.info("searching Book by id " + id);
        return bookDao.findById(id);
    }

    public List<Library> getAllLibrarys() {
        log.info("searching all Librarys");
        return libraryDao.findAll();
    }

    public List<Library> getLibrarysByBook(Book m) {
        log.info("searching Librarys by Book " + m.getId());
        return libraryDao.findByBook(m);
    }

    public Library getLibraryById(int id) {
        log.info("searching Library by id " + id);
        return libraryDao.findById(id);
    }

    public List<Author> getAllAuthors() {
        log.info("searching all Authors");
        return authorDao.findAll();
    }

    public Author getAuthorById(int id) {
        log.info("searching Author by id " + id);
        return authorDao.findById(id);
    }

    @Override
    public Book addBook(Book m) {
        log.info("about to add Book " + m);
        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            m = bookDao.add(m);
            if(m.getTitle().equals("Apocalypse Now")){
                throw new RuntimeException("not yet");
            }
            transactionManager.commit(ts);
        }catch(RuntimeException e){
            transactionManager.rollback(ts);
            throw e;
        }
        return m;
    }

    @Override
    public Author addAuthor(Author d) {
        log.info("about to add Author " + d);
        return authorDao.add(d);
    }

}
