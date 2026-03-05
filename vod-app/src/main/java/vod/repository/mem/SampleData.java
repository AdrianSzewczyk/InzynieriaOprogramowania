package vod.repository.mem;

import vod.model.Library;
import vod.model.Author;
import vod.model.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SampleData {

    static List<Library> librarys = new ArrayList<>();

    static List<Author> authors = new ArrayList<>();

    static List<Book> books = new ArrayList<>();

    static {

        Author king = new Author(1, "Stephen", "King");
        Author orwell = new Author(2, "George", "Orwell");
        Author slowacki = new Author(3, "Juliusz", "Słowacki");
        Author mickiewicz = new Author(4, "Adam", "Mickiewicz");

        Book wielkiMarsz = new Book(1, "Wielki Marsz", "https://ocdn.eu/pulscms-transforms/1/D0gk9kuTURBXy8zYzFhMDRhZS1jOGRiLTQxN2YtOTcwYy1iNjRjZDBkMjc4MDYuanBlZ5GTBc0DFM0BvIGhMAU", king, (float) 4.1);
        Book joyland = new Book(2, "Joyland", "https://fwcdn.pl/fpo/40/98/124098/7521214.6.jpg", king, (float) 4.3);

        Book rok1984 = new Book(3, "Rok 1984", "https://i.iplsc.com/-/00094J03E94SMPSS-C122.jpg", orwell, (float) 3.9);
        Book folwarkZwierzecy = new Book(4, "Folwark Zwierzecy", "https://bi.im-g.pl/im/5b/9b/12/z19510363V,-folwarkZwierzecyl--Nowe-porzadki---rez--Patryk-orwell--plakat.jpg", orwell, (float) 3.1);

        Book kordian = new Book(5, "Kordian", "http://www.gokmichalowo.pl/imprezy2007/kordian/plakat_maly.jpg", slowacki, (float) 4.7);
        Book balladyna = new Book(6, "Balladyna", "http://gapla.fn.org.pl/public/cache/P21829-483x700.jpg", slowacki, (float) 4.4);

        Book panTadeusz = new Book(7, "Pan Tadeusz", "https://m.media-amazon.com/images/M/MV5BNTE5NjAxMTEzNl5BMl5BanBnXkFtZTcwMjYzMDQ0Ng@@._V1_UX182_CR0,0,182,268_AL_.jpg", mickiewicz, (float) 4.9);
        Book dziady = new Book(8, "Dziady", "http://gapla.fn.org.pl/public/cache/P19423-483x700.jpg", mickiewicz, (float) 4.3);

        bind(wielkiMarsz, king);
        bind(joyland, king);

        bind(rok1984, orwell);
        bind(folwarkZwierzecy, orwell);

        bind(kordian, slowacki);
        bind(balladyna, slowacki);

        bind(panTadeusz, mickiewicz);
        bind(dziady, mickiewicz);

        Library gliwice = new Library(1, "Miejska Biblioteka Gliwice", "https://www.gliwice.pl/img/logo.png");
        Library katowice = new Library(2, "Biblioteka Śląska Katowice", "http://www.festiwalfilmuniemego.pl/wp-content/uploads/2015/11/Kino-pod-Baranami.png");
        Library warszawa = new Library(3, "Biblioteka Uniwersytecka Warszawa", "https://i2.wp.com/garretreza.pl/wp-content/uploads/2018/07/nh.jpg");
        Library wroclaw = new Library(4, "Biblioteka Mediateka Wroclaw", "https://static2.s-trojmiasto.pl/zdj/c/n/19/2276/250x0/2276445.jpg");

        bind(gliwice, joyland);
        bind(warszawa, joyland);
        bind(warszawa, wielkiMarsz);
        bind(warszawa, rok1984);

        bind(gliwice, balladyna);
        bind(wroclaw, balladyna);
        bind(wroclaw, panTadeusz);
        bind(katowice, panTadeusz);
        bind(katowice, rok1984);

        books.add(wielkiMarsz);
        books.add(joyland);
        books.add(rok1984);
        books.add(folwarkZwierzecy);
        books.add(kordian);
        books.add(balladyna);
        books.add(panTadeusz);
        books.add(dziady);

        authors.add(king);
        authors.add(orwell);
        authors.add(slowacki);
        authors.add(mickiewicz);

        librarys.add(gliwice);
        librarys.add(katowice);
        librarys.add(warszawa);
        librarys.add(wroclaw);

    }

    private static void bind(Library c, Book m) {
        c.addBook(m);
        m.addLibrary(c);
    }

    private static void bind(Book m, Author d) {
        d.addBook(m);
        m.setAuthor(d);
    }

}
