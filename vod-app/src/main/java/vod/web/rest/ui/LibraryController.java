package vod.web.rest.ui;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Book;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final LibraryService libraryService;
    private final BookService bookService;

    @GetMapping("/libraries")
    String getLibraries(Model model, @RequestParam(value = "bookId", required = false ) Integer bookId) {

        log.info("getLibraries", bookId);
        if(bookId != null) {
            Book book = bookService.getBookById(bookId);
            List<Library> libraries = libraryService.getLibrarysByBook(book);
            model.addAttribute("libraries", libraries);
            model.addAttribute("title", "Books avialable '"+book.getTitle()+"'");
        }else{
            List<Library> libraries = libraryService.getAllLibrarys();
            model.addAttribute("libraries", libraries);
            model.addAttribute("title", "Libraries");
        }

        return "librariesView";
    }
}
