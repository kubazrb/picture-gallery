package pl.wat.prz.engine.repository;

import org.springframework.stereotype.Repository;
import pl.wat.prz.engine.model.QUser;
import pl.wat.prz.engine.model.User;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository {

    private QUser user = QUser.user;

    public User findOne(Long id) {
        return queryFactory.selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    public List<User> findAll() {
        return queryFactory.select(user).from(user).fetch();
    }

    public List<User> findAll(String firstName) {
        return queryFactory.selectFrom(user)
                .where(user.firstname.eq(firstName))
                .fetch();
    }

    public void add(User us) {
        entityManager.persist(us);
    }

    public void remove(Long id) {
        queryFactory.delete(user)
                .where(user.id.eq(id))
                .execute();
    }

    public void update(User us, Long id) {
        queryFactory.update(user)
                .where(user.id.eq(id))
                .set(user, us)
                .execute();
    }
}
