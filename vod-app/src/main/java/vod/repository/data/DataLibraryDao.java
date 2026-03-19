package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Book;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataLibraryDao implements LibraryDao {
    private final LibraryRepository libraryRepository;

    @Override
    public List<Library> findAll() {return libraryRepository.findAll();}

    @Override
    public Library findById(int id) {return libraryRepository.findById(id).orElse(null);}

    @Override
    public List<Library> findByBook(Book book) {
        return libraryRepository.findAllByBookId(book.getId());
    }


    @Override
    public Library save(Library library) {return libraryRepository.save(library);}
}
