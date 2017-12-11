package mahtiuutiset.repository;

import java.util.List;
import mahtiuutiset.domain.NewsObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsObjectRepository extends JpaRepository<NewsObject, Long> {

    public List<NewsObject> findAllByOrderByDateDesc();

}
