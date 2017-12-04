package mahtiuutiset.repository;

import mahtiuutiset.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {
    
}
