package pl.wat.prz.engine.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BaseRepository {

    @PersistenceContext
    EntityManager entityManager;
    JPAQueryFactory queryFactory;
    protected static final Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
