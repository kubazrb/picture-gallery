package pl.wat.prz.engine.repository;

import org.springframework.stereotype.Repository;
import pl.wat.prz.engine.model.Picture;
import pl.wat.prz.engine.model.QPicture;

import java.util.Calendar;
import java.util.List;

@Repository
public class PictureRepository extends BaseRepository {


    private QPicture picture = QPicture.picture;

    public Picture findOne(Long id) {
        return queryFactory.selectFrom(picture)
                .where(picture.id.eq(id))
                .fetchOne();
    }

    public List<Picture> findAll() {
        return queryFactory.select(picture).from(picture).fetch();
    }

    public List<Picture> findAllByName(String name) {
        return queryFactory.selectFrom(picture)
                .where(picture.name.eq(name))
                .fetch();
    }

    public List<Picture> findAllByUser(Long id) {
        return queryFactory.selectFrom(picture)
                .where(picture.id.eq(id))
                .fetch();
    }

    public void add(Picture pic) {
        entityManager.persist(pic);
    }

    public void remove(Long id) {
        queryFactory.delete(picture)
                .where(picture.id.eq(id))
                .execute();
    }

    public void update(Picture pic, Long id) {
        queryFactory.update(picture)
                .where(picture.id.eq(id))
                .set(picture, pic)
                .execute();
    }


    public Long getNextPictureId(Picture currentPic) {
        return queryFactory.select(picture.id)
                .from(picture)
                .where(picture.pdate.after(currentPic.getPdate())
                        .or(picture.pdate.eq(currentPic.getPdate())))
                .orderBy(picture.pdate.asc())
                .limit(1)
                .fetchFirst();
    }

    public Long getPrevPictureId(Picture currentPic) {
        return queryFactory.select(picture.id)
                .from(picture)
                .where(picture.pdate.before(currentPic.getPdate())
                        .or(picture.pdate.eq(currentPic.getPdate())))
                .orderBy(picture.pdate.desc())
                .limit(1)
                .fetchFirst();
    }

    public List<Long> getIdsByDateAllTime() {
        return queryFactory.select(picture.id)
                .from(picture)
                .orderBy(picture.pdate.desc())
                .fetch();
    }

    public List<Long> getIdsByRateThisMonth() {
        int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        return queryFactory.select(picture.id)
                .from(picture)
                .where(picture.pdate.month().eq(currMonth)
                        .and(picture.pdate.year().eq(currYear)))
                .orderBy(picture.points.desc())
                .fetch();
    }

    public List<Long> getIdsByRateThisYear() {
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        return queryFactory.select(picture.id)
                .from(picture)
                .where(picture.pdate.year().eq(currYear))
                .orderBy(picture.points.desc())
                .fetch();
    }

    public List<Long> getIdsByKeyWord(String keyWord) {
        return queryFactory.select(picture.id)
                .from(picture)
                .where(picture.name.toLowerCase()
                        .like("%" + keyWord.toLowerCase() + "%")
                        .or(picture.description.toLowerCase()
                                .like("%" + keyWord.toLowerCase() + "%")))
                .fetch();
    }

    public Long getAmountOfAllPictures() {
        return queryFactory.selectFrom(picture).fetchCount();
    }

    public Long getAmountOfThisMonthPictures() {
        int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        return queryFactory.selectFrom(picture)
                .where(picture.pdate.month().eq(currMonth)
                        .and(picture.pdate.year().eq(currYear)))
                .fetchCount();
    }

    public Long getAmountOfThisYearPictures() {
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        return queryFactory.selectFrom(picture)
                .where(picture.pdate.year().eq(currYear))
                .fetchCount();
    }

    public Long getAmountOfSearchedPictures(String keyWords) {
        return queryFactory.selectFrom(picture)
                .where(picture.name.toLowerCase().like("%" + keyWords.toLowerCase() + "%")
                        .or(picture.description.toLowerCase().like("%" + keyWords.toLowerCase() + "%")))
                .fetchCount();
    }


}