package mahtiuutiset.repository;

import mahtiuutiset.domain.NewsObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsObjectRepository extends JpaRepository<NewsObject, Long> {
    
}
