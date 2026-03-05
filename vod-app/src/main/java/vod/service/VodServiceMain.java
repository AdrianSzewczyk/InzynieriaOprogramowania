package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.repository.LibraryDao;
import vod.repository.BookDao;
import vod.repository.mem.MemLibraryDao;
import vod.repository.mem.MemBookDao;
import vod.model.Library;
import vod.service.impl.LibraryServiceBean;

import java.util.List;
import java.util.Set;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find Librarys!");
        ApplicationContext context = new AnnotationConfigApplicationContext("vod");
        LibraryService service = context.getBean(LibraryService.class);
        LibraryService service2 = context.getBean(LibraryService.class);
        //uzywamy kontekstu do pobrania beana typu interfejsowego LibraryService
        // service use
        List<Library> Librarys = service.getAllLibrarys();
        System.out.println(Librarys.size() + " Librarys found:");
        Librarys.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string "+foo);
    }

}
