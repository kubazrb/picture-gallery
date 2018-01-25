package pl.wat.prz.engine.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BaseRepository {

    @PersistenceContext
    EntityManager entityManager;
    JPAQueryFactory queryFactory;

    @PostConstruct
    public void init(){
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
