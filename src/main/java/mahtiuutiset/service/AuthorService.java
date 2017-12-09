package mahtiuutiset.service;

import java.util.ArrayList;
import java.util.List;
import mahtiuutiset.domain.Author;
import mahtiuutiset.domain.NewsObject;
import mahtiuutiset.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepo;

    public void addNewsToAuthors(List<Author> authors, NewsObject newsObj) {
        for (Author a : authors) {
            authorRepo.findByName(a.getName()).getNews().add(newsObj);
            authorRepo.save(a);
        }
    }

    public List<Author> modifyAuthors(List<String> authors) {
        List<Author> authorList = new ArrayList();
        for (String a : authors) {
            String name = a;
            if (name.isEmpty()) {
                continue;
            }
            Author author = authorRepo.findByName(name);
            if (author == null) {
                author = new Author();
                author.setName(name);
                authorRepo.save(author);
            }
            authorList.add(author);
        }
        return authorList;
    }

}
