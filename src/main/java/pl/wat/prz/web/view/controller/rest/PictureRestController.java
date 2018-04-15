package pl.wat.prz.web.view.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wat.prz.engine.model.Picture;
import pl.wat.prz.engine.service.PictureService;

@RestController
@RequestMapping("/api/picture")
public class PictureRestController {

    @Autowired
    PictureService ps;

    @PostMapping("/point")
    public int point(@RequestBody String url,
                       @RequestParam(value = "action", defaultValue = "plus") String action) {
        System.out.println(Long.valueOf(getIdFromUrl(url)));

        Long currentPicId = Long.valueOf(getIdFromUrl(url));
        Picture pic = ps.findOne(currentPicId);
        if (action.equals("plus"))
            pic.setPoints(pic.getPoints() + 1);
        if (action.equals("minus"))
            pic.setPoints(pic.getPoints() - 1);
        ps.update(pic, currentPicId);
        return pic.getPoints();
    }

    @PostMapping("/data")
    public ResponseEntity<Picture> getData(@RequestBody String url) {
        Picture pic = ps.findOne(Long.valueOf(getIdFromUrl(url)));
        return new ResponseEntity<>(pic, HttpStatus.OK);
    }

    public String getIdFromUrl(String url) {
        String fragments[] = url.split("/");
        return fragments[fragments.length - 1];
    }
}
