package mahtiuutiset.repository;

import java.util.List;
import mahtiuutiset.domain.Category;
import mahtiuutiset.domain.NewsObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByName(String name);
    
    //List<NewsObject> findCategorysNewsByName(String name);
}
