package mahtiuutiset.repository;

import mahtiuutiset.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
