package mk.ukim.finki.emt.lab.ebookstore.repository;
import mk.ukim.finki.emt.lab.ebookstore.model.Book;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findBookByName(String name);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByAuthor_Id(Long id);

    void deleteByName(String name);
}
