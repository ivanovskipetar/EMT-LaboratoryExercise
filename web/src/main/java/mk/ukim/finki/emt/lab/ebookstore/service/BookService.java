package mk.ukim.finki.emt.lab.ebookstore.service;

import mk.ukim.finki.emt.lab.ebookstore.model.Book;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.BookDto;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> findById(Long id);
    Book create(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> create(BookDto bookDto);
    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long id,BookDto bookDto);

    void delete(Long id);
    Integer bookTaken(Long id);
}
