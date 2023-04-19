package mk.ukim.finki.emt.lab.ebookstore.repository;
import mk.ukim.finki.emt.lab.ebookstore.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
