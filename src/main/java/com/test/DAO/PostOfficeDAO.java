package com.test.DAO;

import com.test.entity.PostOffice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PostOfficeDAO {
    private final SessionFactory sessionFactory;

    public PostOfficeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PostOffice getPostOffice(int postOfficeId) {
        PostOffice postOffice = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            postOffice = session.get(PostOffice.class, postOfficeId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return postOffice;
    }
}
