package pl.wat.prz.web.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wat.prz.web.view.controller.base.BaseController;

import java.util.List;

@Controller
@RequestMapping("/picture")
public class PictureController extends BaseController {

    @GetMapping("/{currentPicId}")
    public String view(@PathVariable Long currentPicId, Model model) {
        List<Long> picIds = ps.getIdsByDate();
        Long next = null, prev = null;
        try {
            next = picIds.get(picIds.indexOf(currentPicId) + 1);
            prev = picIds.get(picIds.indexOf(currentPicId) - 1);
            //  IndexOutOfBoundEx -> brak poprzedniej/kolejnej strony do
            //  wyswietlenia. W widoku null nie daje mozliwosci przejsc
            //  dalej/wstecz
        } catch (IndexOutOfBoundsException e) {
            if (next == null)
                prev = picIds.get(picIds.indexOf(currentPicId) - 1);
        } finally {
            model.addAttribute("next", next);
            model.addAttribute("prev", prev);
            model.addAttribute("picture", ps.findOne(currentPicId));
        }
        return "view";
    }
}
