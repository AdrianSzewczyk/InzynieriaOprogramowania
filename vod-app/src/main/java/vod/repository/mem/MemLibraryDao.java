package vod.repository.mem;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vod.repository.LibraryDao;
import vod.model.Library;
import vod.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Repository("LibraryDao")
public class MemLibraryDao implements LibraryDao {

    @Override
    public List<Library> findAll() {
        return SampleData.librarys;
    }

    @Override
    public Library findById(int id) {
        return SampleData.librarys.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Library> findByBook(Book m) {
        return SampleData.librarys.stream().filter(c -> c.getBooks().contains(m)).collect(Collectors.toList());
    }

    @Override
    public Library save(Library library) {
        int maxId = SampleData.librarys.stream()
                .sorted((c1,c2)->c2.getId()-c1.getId())
                .findFirst()
                .map(c->c.getId())
                .orElse(0);
        library.setId(maxId+1);
        SampleData.librarys.add(library);
        return library;
    }
}
