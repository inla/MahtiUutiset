package mahtiuutiset.repository;

import mahtiuutiset.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    //@Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name)")
    Category findByName(String name);//@Param("name") String name);

}
