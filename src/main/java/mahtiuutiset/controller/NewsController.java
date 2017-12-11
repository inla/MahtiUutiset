package mahtiuutiset.controller;

import java.io.IOException;
import java.util.List;
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

/**
 * Kontrolleri.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    /**
     * Luo etusivun.
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String listNewest(Model model) {
        model.addAttribute("news", newsService.findNewest(5));
        return "index";
    }

    /**
     * Listaa kaikki uutiset.
     *
     * @param model
     * @return
     */
    @GetMapping("/kaikki")
    public String listAll(Model model) {
        model.addAttribute("listname", "Kaikki uutiset");
        model.addAttribute("news", newsService.findAllInDateOrder());
        return "list";
    }

    /**
     * Listaa uutiset kategorian perusteella.
     *
     * @param model
     * @param category
     * @return
     */
    @GetMapping("/kategoria/{category}")
    public String viewCategory(Model model, @PathVariable String category) {
        model.addAttribute("listname", category);
        Category c = categoryService.findByName(category);
        if (c != null) {
            model.addAttribute("news", c.getNews());
        }
        return "list";
    }

    /**
     * Luo sivun yksittäiselle uutiselle.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String viewOne(Model model, @PathVariable Long id) {
        newsService.increaseNewsObjectsViews(id);
        model.addAttribute("newsarticle", newsService.getOne(id));
        newsService.addTopListsData(model);
        return "newsarticle";
    }

//    @GetMapping(path = "/{id}/picture", produces = "image/png")
//    @ResponseBody
//    public byte[] getPicture(@PathVariable Long id) {
//        return newsService.getOne(id).getPicture();
//    }
    /**
     * Ohjaa uutisen lisäyssivulle.
     *
     * @return
     */
    @GetMapping("/add")
    public String addNews() {
        return "add";
    }

    /**
     * Luo uutisen.
     *
     * @param title
     * @param lead
     * @param text
     * @param author
     * @param category
     * @return
     */
    @PostMapping("/add")//@RequestParam("picture") MultipartFile picture throws IOException
    public String addNews(@RequestParam String title, @RequestParam String lead,
            @RequestParam String text, @RequestParam List<String> author,
            @RequestParam List<String> category) {

        NewsObject newsObj = new NewsObject(title, lead, text);

//        if (picture.getBytes() != null) {
//            newsObj.setPicture(picture.getBytes());
//        }
        List<Category> categories = categoryService.modifyCategories(category);
        List<Author> authors = authorService.modifyAuthors(author);
        newsObj.setCategories(categories);
        newsObj.setAuthors(authors);
        newsService.save(newsObj);
        categoryService.addNewsToCategories(categories, newsObj);
        authorService.addNewsToAuthors(authors, newsObj);

        return "redirect:/";
    }

    /**
     * Uudelleenohjaa etusivulle.
     *
     * @return
     */
    @GetMapping("/kategoria")
    public String redirectCategories() {
        return "redirect:/";
    }

    /**
     * Palauttaa uutisen muokkaus sivun.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}/edit")
    public String editNews(Model model, @PathVariable Long id) {
        if (newsService.getOne(id) == null) {
            return "redirect:/";
        }
        model.addAttribute("oldarticle", newsService.getOne(id));
        return "edit";
    }

    /**
     * Muokkaa uutista.
     *
     * @param id
     * @param title
     * @param lead
     * @param text
     * @return
     */
    @PostMapping("/{id}/edit")//@RequestParam("picture") MultipartFile picture throws IOException
    public String editNews(@PathVariable Long id, @RequestParam String title, @RequestParam String lead,
            @RequestParam String text) {

        NewsObject newsObj = newsService.getOne(id);

        if (newsObj == null) {
            return "redirect:/";
        }
        newsObj.setTitle(title);
        newsObj.setLead(lead);
        newsObj.setText(text);
//        if (picture.getBytes() != null) {
//            newsObj.setPicture(picture.getBytes());
//        }
        newsService.save(newsObj);

        return "redirect:/" + id;
    }

}
