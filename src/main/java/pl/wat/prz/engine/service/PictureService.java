package pl.wat.prz.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.prz.engine.model.Picture;
import pl.wat.prz.engine.repository.PictureRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    PictureRepository pr;

    public Picture findOne(Long id) {

        return pr.findOne(id);
    }

    public List<Picture> findAll() {
        return pr.findAll();
    }

    public List<Picture> findAll(String name) {
        return pr.findAllByName(name);
    }

    @Transactional
    public void update(Picture pic, Long id) {
        pr.update(pic, id);
    }

    @Transactional
    public void remove(Long id) {
        pr.remove(id);
    }

    public List<Picture> findAllByUser(Long id) {
        return pr.findAllByUser(id);
    }

    @Transactional
    public void add(Picture pic) {
        pr.add(pic);
    }

    public List<Long> getIdsByDate() {
        return pr.getIdsByDateAllTime();
    }

    public List<Long> getIdsByRateThisMonth() {
        return pr.getIdsByRateThisMonth();
    }

    public List<Long> getIdsByRateThisYear() {
        return pr.getIdsByRateThisYear();
    }

    public List<Picture> getPicturesForMainPage(int pageNumber, int pictureAmmount) {
        List<Picture> result = new ArrayList<>();
        List<Long> ids = getIdsByDate();
        for (int i = pageNumber * pictureAmmount; i < pageNumber * pictureAmmount + pictureAmmount; i++) {
            if (ids.size() <= i) break;
            result.add(findOne(ids.get(i)));
        }
        return result;
    }

    public List<Picture> getPicturesForTopMonthPage(int pageNumber, int pictureAmmount) {
        List<Picture> result = new ArrayList<>();
        List<Long> ids = getIdsByRateThisMonth();
        for (int i = pageNumber * pictureAmmount; i < pageNumber * pictureAmmount + pictureAmmount; i++) {
            if (ids.size() <= i) break;
            result.add(findOne(ids.get(i)));
        }
        return result;
    }

    public List<Picture> getPicturesForTopYearPage(int pageNumber, int pictureAmmount) {
        List<Picture> result = new ArrayList<>();
        List<Long> ids = getIdsByRateThisYear();
        for (int i = pageNumber * pictureAmmount; i < pageNumber * pictureAmmount + pictureAmmount; i++) {
            if (ids.size() <= i) break;
            result.add(findOne(ids.get(i)));
        }
        return result;
    }

    public List<Picture> getPicturesForSearchPage(int pageNumber, int pictureAmmount, String keyWord) {
        List<Picture> result = new ArrayList<>();
        List<Long> ids = getIdsByKeyWord(keyWord);
        for (int i = pageNumber * pictureAmmount; i < pageNumber * pictureAmmount + pictureAmmount; i++) {
            if (ids.size() <= i) break;
            result.add(findOne(ids.get(i)));
        }
        return result;
    }

    public Long getAmountOfAllPictures() {
        return pr.getAmountOfAllPictures();
    }

    public Long getAmountOfThisMonthPictures() {
        return pr.getAmountOfThisMonthPictures();
    }

    public Long getAmountOfThisYearPictures() {
        return pr.getAmountOfThisYearPictures();
    }

    public Long getNextPictureId(Picture currentPic) {
        return pr.getNextPictureId(currentPic);
    }

    public Long getPrevPictureId(Picture currentPic) {
        return pr.getPrevPictureId(currentPic);
    }

    public Long getAmountOfSearchedPictures(String keyWords) {
        return pr.getAmountOfSearchedPictures(keyWords);
    }

    public List<Long> getIdsByKeyWord(String keyWord) {
        return pr.getIdsByKeyWord(keyWord);
    }

}
