package mahtiuutiset.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Kategoria-luokka.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstractPersistable<Long> {

    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<NewsObject> news = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
