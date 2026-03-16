package vod.web.rest;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import vod.model.Author;
import vod.service.BookService;
import vod.web.rest.dto.BookDTO;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {
    private final BookService bookService;

    @Override
    public boolean supports(Class<?> clazz){return clazz.isAssignableFrom(BookDTO.class);}

    @Override
    public void validate(Object target, Errors errors){
        BookDTO bookDTO = (BookDTO) target;
        Author author = bookService.getAuthorById(bookDTO.getAuthorId());
        if(author == null){
            errors.rejectValue("authorId", "book.author.missing");
        }
    }
}
