package mk.ukim.finki.emt.lab.ebookstore.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Category;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Role;
import mk.ukim.finki.emt.lab.ebookstore.service.AuthorService;
import mk.ukim.finki.emt.lab.ebookstore.service.BookService;
import mk.ukim.finki.emt.lab.ebookstore.service.CountryService;
import mk.ukim.finki.emt.lab.ebookstore.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;
    private final UserService userService;

    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService, UserService userService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        userService.create("admin", "a", "librarian", Role.LIBRARIAN);
        userService.create("user", "u", "client", Role.USER);
        countryService.create("Macedonia", "Europe");
        countryService.create("China", "Asia");
        countryService.create("Chicago", "North America");
        authorService.create("Bojan", "Spasovski", 1L);
        authorService.create("Vlado", "Damjanovski", 2L);
        authorService.create("Riste", "Markovski", 3L);
        authorService.create("Stefan", "Ilievski", 1L);
        authorService.create("Marko", "Stefanovski", 2L);
        authorService.create("Ilija", "Spasovski", 3L);
        authorService.create("Kiril", "Petrovski", 1L);
        authorService.create("Matej", "Pavlovski", 2L);
        authorService.create("Hristijan", "Kolevski", 3L);
        bookService.create("WimHof", Category.NOVEL, 1L, 4);
        bookService.create("Think and Grow Rich", Category.THRILER, 2L, 10);
        bookService.create("Rich Dad Poor Dad", Category.HISTORY, 3L, 12);
        bookService.create("IT STARTS WITH US", Category.NOVEL, 4L, 44);
        bookService.create("IT ENDS WITH US", Category.THRILER, 5L, 101);
        bookService.create("LESSONS IN CHEMISTRY", Category.HISTORY, 6L, 122);
        bookService.create("THE SEVEN HUSBANDS OF EVELYN HUGO", Category.THRILER, 7L, 101);
        bookService.create("THE BODY KEEPS THE SCORE", Category.HISTORY, 8L, 14);
        bookService.create("HOMECOMING", Category.HISTORY, 9L, 12);
    }
}
