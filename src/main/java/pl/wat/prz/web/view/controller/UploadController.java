package pl.wat.prz.web.view.controller;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wat.prz.engine.exceptions.FormDataException;
import pl.wat.prz.engine.exceptions.IncorrectResolutionOfImageException;
import pl.wat.prz.engine.exceptions.IncorrectSizeOfImageException;
import pl.wat.prz.engine.exceptions.IncorrectTypeOfImageException;
import pl.wat.prz.engine.model.Picture;
import pl.wat.prz.engine.service.UserService;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @Autowired
    UserService us;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");


    @PostMapping("")
    public String add(@RequestParam("file") MultipartFile file,
                      @Valid @ModelAttribute("picture") Picture picture,
                      BindingResult result,
                      Model model) {

        try {
            if (result.hasErrors()) throw new FormDataException();
            validateImage(file);
            picture.setImage(file.getBytes());
            picture.setPdate(new Date());
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
            return "upload";
        } catch (FormDataException e) {
            String m = result.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("error", m);
            return "upload";
        }
        ps.add(picture);
        model.addAttribute("uploadedImage", picture);
        model.addAttribute("success", "upload-success");

        return "upload";
    }

    @GetMapping("")
    public String addForm(Model model) {
        model.addAttribute("picture", new Picture());
        return "upload";
    }

    public void validateImage(MultipartFile image) throws IOException {
        if (!image.getContentType().startsWith("image"))
            throw new IncorrectTypeOfImageException();          // .jpg .png ..

        File tmp = new File("tmp");
        Files.write(image.getBytes(), tmp);
        BufferedImage bimg = ImageIO.read(tmp);
        if (image.getSize() > 10485760)
            throw new IncorrectSizeOfImageException();          //10Mb max
        if (bimg.getHeight() > 1200 || bimg.getWidth() > 890)
            throw new IncorrectResolutionOfImageException();    //1200x890 max
    }


}
