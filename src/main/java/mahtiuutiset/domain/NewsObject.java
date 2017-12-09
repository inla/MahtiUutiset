package mahtiuutiset.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NewsObject extends AbstractPersistable<Long>{
    private String title;
    private String lead; //ingressi
    private String text;
    //@Lob //heroku ongelma
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;
    private String date;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
    private int views;

    public NewsObject(String title, String lead, String text, byte[] picture) {
        this.title = title;
        this.lead = lead;
        this.text = text;
        this.picture = picture;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public NewsObject(String title, String lead, String text) {
        this.title = title;
        this.lead = lead;
        this.text = text;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        this.views = 0;
    }
    
    public void increaseViews() {
        this.views++;
    }

}
