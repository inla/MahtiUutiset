package mahtiuutiset.controller;

import java.util.ArrayList;
import java.util.List;
import mahtiuutiset.domain.Category;
import mahtiuutiset.domain.NewsObject;
import mahtiuutiset.repository.AuthorRepository;
import mahtiuutiset.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mahtiuutiset.repository.NewsObjectRepository;

@Controller
public class NewsController {

    @Autowired
    private NewsObjectRepository newsRepo;
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private AuthorRepository authorRepo;

    @GetMapping("/")
    public String listNewest(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "date");
        model.addAttribute("news", newsRepo.findAll(pageable));
        return "index";
    }
        
    @GetMapping("/kategoriat/{category}")
    public String viewCategory(Model model, @PathVariable String category) {
        model.addAttribute("category", categoryRepo.findCategoryByName(category));
        model.addAttribute("news", categoryRepo.findCategorysNewsByName(category));
        return "category";
    }
    
    @GetMapping("/{id}")
    public String viewOne(Model model, @PathVariable Long id) {
        model.addAttribute("newsarticle", newsRepo.getOne(id));
        
        return "newsarticle";
    }
    
    @GetMapping("/add")
    public String getAddNews() {
        return "add";
    }
    
    @PostMapping("/add")
    public String postAddNews(@RequestParam String title, @RequestParam String lead,
            @RequestParam String text, @RequestParam String author, 
            @RequestParam List<String> category) {
        
        List<Category> categories = modifyCategories(category);
        
        NewsObject newsObj = new NewsObject();
        newsObj.setTitle(title);
        newsObj.setLead(lead);
        newsObj.setText(text);
        newsObj.setCategories(categories);
        //ni.setAuthors(authors);
        //ni.setDate(new LocalDateTime());
        
        newsRepo.save(newsObj);
        
        for (Category c : categories) { //sama authoreille
            c.getNews().add(newsObj);
            categoryRepo.save(c);
        }
        
        
        return "redirect:/";
    }
    
//    ?
//    @GetMapping("/categories")
//    public String getCategories(Model model) {
//        return "";
//    }

    private List<Category> modifyCategories(List<String> categories) {
        List<Category> categoryList = new ArrayList();
        for (String categ : categories) {
            String name = categ;
            if (name.isEmpty()) {
                continue;
            }
            Category category = categoryRepo.findCategoryByName(name);
            if (category == null) {
                category = new Category();
                category.setName(name);
                categoryRepo.save(category);
            }
            categoryList.add(category);
        }
        return categoryList;
    }
}
