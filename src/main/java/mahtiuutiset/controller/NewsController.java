package mahtiuutiset.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import mahtiuutiset.domain.Author;
import mahtiuutiset.domain.Category;
import mahtiuutiset.domain.NewsObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mahtiuutiset.service.AuthorService;
import mahtiuutiset.service.CategoryService;
import mahtiuutiset.service.NewsService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

//    @PostConstruct
//    private void init() {
////        Category kotimaa = new Category("Kotimaa");
////        categoryRepo.save(kotimaa);
////        Category ulkomaat = new Category("Ulkomaat");
////        categoryRepo.save(ulkomaat);
////        Category kulttuuri = new Category("Kulttuuri");
////        categoryRepo.save(kulttuuri);
////        Category tiede = new Category("Tiede");
////        categoryRepo.save(tiede);
////        Category urheilu = new Category("Urheilu");
////        categoryRepo.save(urheilu);
//        List<String> categories = new ArrayList();
//        categories.add("Kotimaa");
//        categories.add("Ulkomaat");
//        categories.add("Kulttuuri");
//        categories.add("Tiede");
//        categories.add("Urheilu");
//        
//        for (String s : categories) {
//            Category category = categoryRepo.findByName(s);
//            if (category == null) {
//                category = new Category();
//                category.setName(s);
//                categoryRepo.save(category);
//            }
//        }
//    }
    @GetMapping("/")
    public String listNewest(Model model) {
        model.addAttribute("news", newsService.findNewest(5));
        return "index";
    }

    @GetMapping("/kaikki")
    public String listAll(Model model) {
        model.addAttribute("listname", "Kaikki uutiset");
        model.addAttribute("news", newsService.findAllInDateOrder());
        return "list";
    }

    @GetMapping("/kategoria/{category}")
    public String viewCategory(Model model, @PathVariable String category) {
        model.addAttribute("listname", category);
        //model.addAttribute("category", categoryRepo.findByName(category));
        Category c = categoryService.findByName(category);
        if (c != null) {
            model.addAttribute("news", c.getNews());
        }
        //newsService.addFooterData(model);
        return "list";
    }

    @GetMapping("/{id}")
    public String viewOne(Model model, @PathVariable Long id) {
        model.addAttribute("newsarticle", newsService.getOne(id));
        newsService.increaseNewsObjectsViews(id);
        newsService.addFooterData(model);
        return "newsarticle";
    }

    @GetMapping(path = "/{id}/picture", produces = "image/png")
    @ResponseBody
    public byte[] getPicture(@PathVariable Long id) {
        return newsService.getOne(id).getPicture();
    }

    @GetMapping("/add")
    public String addNews() {
        return "add";
    }

    @PostMapping("/add")
    public String addNews(@RequestParam String title, @RequestParam String lead,
            @RequestParam String text, @RequestParam("picture") MultipartFile picture,
            @RequestParam List<String> author, @RequestParam List<String> category) throws IOException {

        
        NewsObject newsObj = new NewsObject(title, lead, text);

        if (picture.getBytes() != null) {
            newsObj.setPicture(picture.getBytes());
        }
        List<Category> categories = categoryService.modifyCategories(category);
        List<Author> authors = authorService.modifyAuthors(author);
        newsObj.setCategories(categories);
        newsObj.setAuthors(authors);
        newsService.save(newsObj);
        categoryService.addNewsToCategories(categories, newsObj);
        authorService.addNewsToAuthors(authors, newsObj);

        return "redirect:/";
    }

    @GetMapping("/kategoria")
    public String redirectCategories() {
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editNews(Model model, @PathVariable Long id) {
        if (newsService.getOne(id) == null) {
            return "redirect:/";
        }
        model.addAttribute("oldarticle", newsService.getOne(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String editNews(@PathVariable Long id, @RequestParam String title, @RequestParam String lead,
            @RequestParam String text, @RequestParam("picture") MultipartFile picture) throws IOException {

        NewsObject newsObj = newsService.getOne(id);

        if (newsObj == null) {
            return "redirect:/";
        }
        newsObj.setTitle(title);
        newsObj.setLead(lead);
        newsObj.setText(text);
        if (picture.getBytes() != null) {
            newsObj.setPicture(picture.getBytes());
        }
        newsService.save(newsObj);

        return "redirect:/" + id;
    }

}
