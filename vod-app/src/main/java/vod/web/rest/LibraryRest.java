package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Book;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;

import java.util.List;
import java.util.Locale;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class LibraryRest {
    private final LibraryService libraryService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final LibraryValidator libraryValidator;



    @GetMapping("/libraries")
    public List<Library> getLibrarys(@RequestParam(value = "phrase", required = false) String title,
                                     @RequestHeader(value = "custom-header", required = false) String customHeader) {
        log.info("about to retrieve Librarys list");
        log.info("title: {}", title);
        log.info("customHeader: {}", customHeader);
        if(title!=null && title.equals("foo")) {
            throw new IllegalArgumentException("Foo!");
        }
        List<Library> librarys = libraryService.getAllLibrarys();
        log.info("{} Librarys found", librarys.size());
        return librarys;
    }

    @GetMapping("/libraries/{id}")
    ResponseEntity<Library> getLibrary(@PathVariable("id") int id) {
        log.info("about to retrieve Library with id {}", id);
        Library library = libraryService.getLibraryById(id);
        log.info("{} Library found", library);
        if(library != null) {
            return ResponseEntity.status(200).body(library);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books/{bookId}/libraries")
    ResponseEntity<List<Library>> getBookLibrary(@PathVariable("bookId") int bookId) {
        log.info("about to retrieve Book Library with id {}", bookId);
        Book book = bookService.getBookById(bookId);
        if(book == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            List<Library> librarys = libraryService.getLibrarysByBook(book);
            log.info("{} Librarys found", librarys.size(), book.getTitle());
            return ResponseEntity.ok(librarys);
        }
    }

    @PostMapping("/libraries")
    ResponseEntity<?> createLibrary(@Validated @RequestBody Library library, Errors errors, HttpServletRequest request) {
        log.info("about to add library {}", library);
        if(errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            //Locale locale = Locale.forLanguageTag("pl"); // czyli "pl"
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> oe.getDefaultMessage())
                    .reduce("errors:\n", (accu, msg) -> accu + msg + "\n");
            return ResponseEntity.badRequest().body(errorMessage);
        }
        library = libraryService.addLibrary(library);
        log.info("{} Library added", library);
        return ResponseEntity.status(HttpStatus.CREATED).body(library);
    }
}
