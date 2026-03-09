package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RequiredArgsConstructor
public class VodAdvice {
    private final LibraryValidator libraryValidator;

    @InitBinder("/library")
    void initBinder(WebDataBinder binder) {binder.addValidators(libraryValidator);}
}
