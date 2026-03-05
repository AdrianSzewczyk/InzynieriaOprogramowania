package vod.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import vod.model.Library;
import vod.model.Book;
import vod.repository.LibraryDao;
import vod.repository.BookDao;
import vod.service.LibraryService;

import java.util.List;
import java.util.logging.Logger;
@Component
@Scope("prototype")
public class LibraryServiceBean implements LibraryService {

    private static final Logger log = Logger.getLogger(LibraryService.class.getName());

    private LibraryDao libraryDao;
    private BookDao bookDao;

    public LibraryServiceBean(@Qualifier("LibraryDao") LibraryDao libraryDao, BookDao bookDao) {
        log.info("creating Library service bean");
        this.libraryDao = libraryDao;
        this.bookDao = bookDao;
    }

    @Override
    public Library getLibraryById(int id) {
        log.info("searching Library by id " + id);
        return libraryDao.findById(id);
    }

    @Override
    public List<Book> getBooksInLibrary(Library c) {
        log.info("searching Books played in Library " + c.getId());
        return bookDao.findByLibrary(c);
    }

    @Override
    public List<Library> getAllLibrarys() {
        log.info("searching all Librarys");
        return libraryDao.findAll();
    }

    @Override
    public List<Library> getLibrarysByBook(Book m) {
        log.info("searching Librarys by Book " + m.getId());
        return libraryDao.findByBook(m);
    }

}
