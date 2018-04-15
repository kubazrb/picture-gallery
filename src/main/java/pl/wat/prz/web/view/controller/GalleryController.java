package pl.wat.prz.web.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wat.prz.web.view.controller.base.BaseController;

@Controller
@RequestMapping("/")
public class GalleryController extends BaseController {

    @GetMapping("")
    public String mainPage(Model model) {
        return "redirect:/0";
    }


    @GetMapping("/{pageNumber}")
    public String gallery(@PathVariable int pageNumber, Model model) {
        model.addAttribute("title", "null");
        return addAttributes(model, ps.getAmountOfAllPictures(),
                ps.getPicturesForMainPage(pageNumber,
                        amountOfPicturesOnPage), "");
    }

    @GetMapping("/top/month/{pageNumber}")
    public String topMonth(@PathVariable int pageNumber, Model model) {
        model.addAttribute("title", "top-month-title");
        return addAttributes(model, ps.getAmountOfThisMonthPictures(),
                ps.getPicturesForTopMonthPage(pageNumber,
                        amountOfPicturesOnPage), "/top/month/");
    }

    @GetMapping("/top/year/{pageNumber}")
    public String topYear(@PathVariable int pageNumber, Model model) {
        model.addAttribute("title", "top-year-title");
        return addAttributes(model, ps.getAmountOfThisYearPictures(),
                ps.getPicturesForTopYearPage(pageNumber,
                        amountOfPicturesOnPage), "/top/year/");
    }


}
