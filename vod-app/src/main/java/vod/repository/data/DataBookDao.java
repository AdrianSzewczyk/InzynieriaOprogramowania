package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.repository.BookDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataBookDao implements BookDao {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {return bookRepository.findAll();}

    @Override
    public Book findById(int id){return bookRepository.findById(id).orElse(null);}

    @Override
    public List<Book> findByAuthor(Author a){return bookRepository.findAllByAuthor(a);}

    @Override
    public List<Book> findByLibrary(Library library){return bookRepository.findAllByLibrarysContaining(library);}

    @Override
    public Book add(Book b){return bookRepository.save(b);}
}
