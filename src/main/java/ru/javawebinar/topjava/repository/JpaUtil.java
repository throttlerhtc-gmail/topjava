package ru.javawebinar.topjava.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile({"jpa", "datajpa"})
public class JpaUtil {

    @PersistenceContext
    private EntityManager em;

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) em.getDelegate();
        SessionFactory sf = s.getSessionFactory();
//        sf.evict(User.class);
//        sf.getCache().evictEntity(User.class, BaseEntity.START_SEQ);
//        sf.getCache().evictEntityRegion(User.class);
        sf.getCache().evictAllRegions();
    }
}
