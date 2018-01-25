package pl.wat.prz.web.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wat.prz.engine.model.Picture;

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

    @PostMapping("/{currentPicId}")
    public String point(@PathVariable Long currentPicId, Model model,
                        @RequestParam(value = "action", required = true) String action) {
        Picture pic = ps.findOne(currentPicId);
        if (action.equals("plus"))
            pic.setPoints(pic.getPoints() + 1);
        if (action.equals("minus"))
            pic.setPoints(pic.getPoints() - 1);
        ps.update(pic, currentPicId);
        return "redirect:" + currentPicId;
    }
}
