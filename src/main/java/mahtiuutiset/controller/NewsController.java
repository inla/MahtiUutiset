package mahtiuutiset.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mahtiuutiset.domain.Author;
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
    
    @PostConstruct
    private void init() {
//        Category kotimaa = new Category("Kotimaa");
//        categoryRepo.save(kotimaa);
//        Category ulkomaat = new Category("Ulkomaat");
//        categoryRepo.save(ulkomaat);
//        Category kulttuuri = new Category("Kulttuuri");
//        categoryRepo.save(kulttuuri);
//        Category tiede = new Category("Tiede");
//        categoryRepo.save(tiede);
//        Category urheilu = new Category("Urheilu");
//        categoryRepo.save(urheilu);
        List<String> categories = new ArrayList();
        categories.add("Kotimaa");
        categories.add("Ulkomaat");
        categories.add("Kulttuuri");
        categories.add("Tiede");
        categories.add("Urheilu");
        
        for (String s : categories) {
            Category category = categoryRepo.findByName(s);
            if (category == null) {
                category = new Category();
                category.setName(s);
                categoryRepo.save(category);
            }
        }
    }
//
    @GetMapping("/")
    public String listNewest(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "date");
        model.addAttribute("news", newsRepo.findAll(pageable));
        return "index";
    }
    
    @GetMapping("/kaikki")
    public String listAll(Model model) {
        model.addAttribute("listname", "Kaikki uutiset");
        model.addAttribute("news", newsRepo.findAllByOrderByDateDesc());
        return "list";
    }
    
    @GetMapping("/kategoria/{category}")
    public String viewCategory(Model model, @PathVariable String category) {
        model.addAttribute("listname", category);
        //model.addAttribute("category", categoryRepo.findByName(category));
        model.addAttribute("news", categoryRepo.findByName(category).getNews());
        return "list";
    }
    
    @GetMapping("/{id}")
    public String viewOne(Model model, @PathVariable Long id) {
        model.addAttribute("newsarticle", newsRepo.getOne(id));
        
        return "newsarticle";
    }
    
    @GetMapping("/add")
    public String addNews() {
        return "add";
    }
    
    @PostMapping("/add")
    public String addNews(@RequestParam String title, @RequestParam String lead,
            @RequestParam String text, @RequestParam List<String> author, 
            @RequestParam List<String> category) {
        
        List<Category> categories = modifyCategories(category);
        List<Author> authors = modifyAuthors(author);
        
        NewsObject newsObj = new NewsObject(title, lead, text);
        newsObj.setCategories(categories);
        newsObj.setAuthors(authors);
        
        newsRepo.save(newsObj);
        
        for (Category c : categories) {
            categoryRepo.findByName(c.getName()).getNews().add(newsObj);
            categoryRepo.save(c);
        }
        for (Author a : authors) {
            authorRepo.findByName(a.getName()).getNews().add(newsObj);
            authorRepo.save(a);
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/kategoria")
    public String redirectCategories() {
        return "redirect:/";
    }

    private List<Category> modifyCategories(List<String> categories) {
        List<Category> categoryList = new ArrayList();
        for (String c : categories) {
            String name = c;
            if (name.isEmpty()) {
                continue;
            }
            Category category = categoryRepo.findByName(name);
//            if (category == null) {
//                category = new Category();
//                category.setName(name);
//                categoryRepo.save(category);
//            }
            categoryList.add(category);
        }
        return categoryList;
    }

    private List<Author> modifyAuthors(List<String> authors) {
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
