package mahtiuutiset.domain;

import java.time.LocalDateTime;
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
public class NewsItem extends AbstractPersistable<Long>{
    private String title;
    private String lead; //ingressi
    private String text;
    //private ? picture;
    private LocalDateTime date;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
    private int views = 0;

}
