package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.repository.LibraryDao;
import vod.model.Library;
import vod.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Component("LibraryDao")
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
}
