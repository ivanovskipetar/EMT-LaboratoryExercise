package mk.ukim.finki.emt.lab.ebookstore.web;


import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.ebookstore.model.Book;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.BookDto;
import mk.ukim.finki.emt.lab.ebookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;


    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping
    public List<Book> getBooks() {
        return this.bookService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.create(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());

    }
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id){
        this.bookService.delete(id);
//        if(this.bookService.findById(id).isEmpty())
//            return ResponseEntity.ok().build();
//        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/take/{id}")
    public ResponseEntity  takeBook(@PathVariable Long id){
        Book book = this.bookService.findById(id).orElseThrow(()->new BookNotFoundException(id));
        int oldNumberOfCopies = book.getAvailableCopies();
        int newNumberOfCopies = this.bookService.bookTaken(id);
        if(oldNumberOfCopies-1 == newNumberOfCopies)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
