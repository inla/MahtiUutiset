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

@Service
public class NewsService {

    @Autowired
    private NewsObjectRepository newsRepo;

    public Page<NewsObject> findNewest(int howMany) {
        Pageable pageable = PageRequest.of(0, howMany, Sort.Direction.DESC, "date");
        return newsRepo.findAll(pageable);
    }

    public List<NewsObject> findAllInDateOrder() {
        return newsRepo.findAllByOrderByDateDesc();
    }

    public NewsObject getOne(Long id) {
        return newsRepo.getOne(id);
    }

    public void save(NewsObject newsObj) {
        newsRepo.save(newsObj);
    }

    public void increaseNewsObjectsViews(Long id) {
        newsRepo.getOne(id).increaseViews();
        save(newsRepo.getOne(id));
    }

    public void addFooterData(Model model) {
        model.addAttribute("newest", findNewest(10));
        model.addAttribute("popularest", findMostPopular(10));
    }

    private Object findMostPopular(int howMany) {
        Pageable pageable = PageRequest.of(0, howMany, Sort.Direction.DESC, "views");
        return newsRepo.findAll(pageable);
    }
}
