package vod.service;

import vod.model.Library;
import vod.model.Book;

import java.util.List;

public interface LibraryService {
//api zwraca nam wszystkie kina
    Library getLibraryById(int id);

    List<Library> getAllLibrarys();

    List<Library> getLibrarysByBook(Book m);

    List<Book> getBooksInLibrary(Library c);
    Library addLibrary(Library c);

}
