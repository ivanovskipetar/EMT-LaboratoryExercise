package mk.ukim.finki.emt.lab.ebookstore.service.impl;


import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.NoAvailableCopiesException;
import mk.ukim.finki.emt.lab.ebookstore.model.Author;
import mk.ukim.finki.emt.lab.ebookstore.model.Book;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.BookDto;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Category;
import mk.ukim.finki.emt.lab.ebookstore.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.ebookstore.repository.BookRepository;
import mk.ukim.finki.emt.lab.ebookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    @Transactional
    public Book create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name,category,author,availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Category category = Enum.valueOf(Category.class, bookDto.getCategory());
        Book book = new Book(bookDto.getName(),category,author,bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id,BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(Enum.valueOf(Category.class, bookDto.getCategory()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
    }
    @Override
    public Integer bookTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        int availableCopies = book.getAvailableCopies();
        if (availableCopies == 0){
            throw new NoAvailableCopiesException(id);
        }
        else {
            availableCopies = availableCopies-1;
            book.setAvailableCopies(availableCopies);
            this.bookRepository.save(book);
        }
        return book.getAvailableCopies();
    }
}
