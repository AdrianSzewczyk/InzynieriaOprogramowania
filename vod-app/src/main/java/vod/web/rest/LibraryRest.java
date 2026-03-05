package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vod.model.Library;
import vod.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LibraryRest {
    private final LibraryService libraryService;

    @GetMapping("/libraries")
    public List<Library> getLibrarys() {
        log.info("about to retrieve Librarys list");
        List<Library> librarys = libraryService.getAllLibrarys();
        log.info("{} Librarys found", librarys.size());
        return librarys;
    }
}
