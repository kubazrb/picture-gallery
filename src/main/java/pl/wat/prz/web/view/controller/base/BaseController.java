package pl.wat.prz.web.view.controller.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import pl.wat.prz.engine.model.Picture;
import pl.wat.prz.engine.service.PictureService;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;


public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected PictureService ps;
    public static final int amountOfPicturesOnPage = 10;

    public String addAttributes(Model model, Long amountOfPictures, List<Picture> pictures, String path) {
        try {
            int p;
            p = amountOfPictures % 10 == 0 ? 1 : 0;
            model.addAttribute("lastPage", (amountOfPictures / amountOfPicturesOnPage) - p);
            model.addAttribute("pictures", pictures);
            model.addAttribute("path", path);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "redirect:/0";
        }
        return "gallery";
    }

}
