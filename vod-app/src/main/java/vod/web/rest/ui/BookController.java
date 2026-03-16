package vod.web.rest.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;
    private final LibraryService libraryService;

    @GetMapping("/books")
    String getBooks(Model model, @RequestParam(value = "libraryId",required = false) Integer libraryId,
                    @RequestParam(value = "authorId",required = false) Integer authorId) {
        log.info("about to get books",libraryId);
        if(libraryId != null) {
            Library library = libraryService.getLibraryById(libraryId);
            List<Book> books = libraryService.getBooksInLibrary(library);
            model.addAttribute("books", books);
            model.addAttribute("title","Books in Library");
        }else if(authorId != null) {
            Author author = bookService.getAuthorById(authorId);
            List<Book> books = bookService.getBooksByAuthor(author);
            model.addAttribute("books", books);
            model.addAttribute("title","Books writed by '"+author.getLastName()+"'");
        }
        else{
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
            model.addAttribute("title","Books");
        }return "booksView";

    }

}
