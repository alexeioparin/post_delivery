package com.test.DAO;

import com.test.entity.Delivery;
import com.test.entity.DeliveryRouting;
import com.test.entity.PostOffice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class DeliveryDAO {
    private final SessionFactory sessionFactory;

    public DeliveryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Delivery getDelivery(int deliveryId) {
        Delivery delivery = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            delivery = session.get(Delivery.class, deliveryId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return delivery;
    }

    public Delivery getDeliveryWithHistory(int deliveryId) {
        Delivery delivery = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Delivery> query = session.createQuery("from Delivery d " +
                    "join fetch d.deliveryRoutingList where d.deliveryId = :id and d.isReceived = false",
                    Delivery.class);
            query.setParameter("id", deliveryId);
            delivery = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return delivery;
    }

    public Delivery saveDelivery(Delivery delivery, PostOffice postOffice) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(delivery);
            session.save(new DeliveryRouting(delivery, postOffice, LocalDateTime.now()));
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return delivery;
    }

    public boolean setDeliveryStatus(Delivery delivery, boolean isComplete) {
        Session session = sessionFactory.openSession();
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            delivery = session.get(Delivery.class, delivery.getDeliveryId());
            delivery.setReceived(isComplete);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            isSuccess = false;
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return isSuccess;
    }
}
