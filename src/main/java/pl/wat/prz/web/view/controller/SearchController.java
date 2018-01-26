package pl.wat.prz.web.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {

    @GetMapping("")
    public String search(@RequestParam("keyword") String keyword,
                         @RequestParam("pageNumber") int pageNumber,
                         Model model) {
        Long searchResultCount = ps.getAmountOfSearchedPictures(keyword);
        if (searchResultCount == 0 || keyword.equals("")) return "error/noresult";
        model.addAttribute("title", "search-result");
        model.addAttribute("keyword", "\"" + keyword + "\"");
        model.addAttribute("pageNumber", pageNumber);
        addAttributes(model,
                searchResultCount,
                ps.getPicturesForSearchPage(pageNumber, amountOfPicturesOnPage, keyword),
                "/search?keyword=" + keyword + "&pageNumber=");
        return "gallery";
    }
}
