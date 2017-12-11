package mahtiuutiset.service;

import java.util.ArrayList;
import java.util.List;
import mahtiuutiset.domain.Category;
import mahtiuutiset.domain.NewsObject;
import mahtiuutiset.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Category findByName(String category) {
        return categoryRepo.findByName(category);
    }

    public List<Category> modifyCategories(List<String> categories) {
        List<Category> categoryList = new ArrayList();
        for (String c : categories) {
            String name = c;
            if (name.isEmpty()) {
                continue;
            }
            Category category = findByName(name);
            if (category == null) {
                category = new Category();
                category.setName(name);
                save(category);
            }
            categoryList.add(category);
        }
        return categoryList;
    }

    public void save(Category c) {
        categoryRepo.save(c);
    }

    public void addNewsToCategories(List<Category> categories, NewsObject newsObj) {
        for (Category c : categories) {
            findByName(c.getName()).getNews().add(newsObj);
            save(c);
        }
    }
}
