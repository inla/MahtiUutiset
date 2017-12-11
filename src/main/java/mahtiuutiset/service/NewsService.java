package mahtiuutiset.service;

import java.util.List;
import mahtiuutiset.domain.NewsObject;
import mahtiuutiset.repository.NewsObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Palvelu uutisten tallettamiselle ja näyttämiselle.
 * @author inka
 */
@Service
public class NewsService {

    @Autowired
    private NewsObjectRepository newsRepo;

    /**
     * Etsii uusimmat uutiset.
     * @param howMany
     * @return 
     */
    public Page<NewsObject> findNewest(int howMany) {
        Pageable pageable = PageRequest.of(0, howMany, Sort.Direction.DESC, "date");
        return newsRepo.findAll(pageable);
    }

    /**
     * Etsii kaikki uutiset aikajärjestyksessä.
     * @return 
     */
    public List<NewsObject> findAllInDateOrder() {
        return newsRepo.findAllByOrderByDateDesc();
    }

    /**
     * Etsii tietyn uutisen.
     * @param id
     * @return 
     */
    public NewsObject getOne(Long id) {
        return newsRepo.getOne(id);
    }

    /**
     * Tallettaa uutisen tietokantaan.
     * @param newsObj 
     */
    public void save(NewsObject newsObj) {
        newsRepo.save(newsObj);
    }

    /**
     * Kasvattaa uutisen lukukertoja.
     * @param id 
     */
    public void increaseNewsObjectsViews(Long id) {
        newsRepo.getOne(id).increaseViews();
        save(newsRepo.getOne(id));
    }

    /**
     * Etsii uutissivulla näytettävien TOP10 listojen tiedot.
     * @param model 
     */
    public void addTopListsData(Model model) {
        model.addAttribute("newest", findNewest(10));
        model.addAttribute("popularest", findMostPopular(10));
    }

    /**
     * Etsii suosituimmat eli useimmin avatut uutiset.
     * @param howMany
     * @return 
     */
    private Object findMostPopular(int howMany) {
        Pageable pageable = PageRequest.of(0, howMany, Sort.Direction.DESC, "views");
        return newsRepo.findAll(pageable);
    }
}
