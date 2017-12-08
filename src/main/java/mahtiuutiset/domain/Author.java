package mahtiuutiset.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends AbstractPersistable<Long>{
    private String name;
    @ManyToMany
    private List<NewsObject> news = new ArrayList<>();
    

}
