package com.test.DAO;

import com.test.entity.DeliveryRouting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public class DeliveryRoutingDAO {
    private final SessionFactory sessionFactory;

    public DeliveryRoutingDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean setDeliveryDepartureTime(int deliveryId, LocalDateTime departureTime) {
        boolean isSuccess = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query<DeliveryRouting> query = session.createQuery("from DeliveryRouting " +
                    " where delivery.deliveryId = :id and departureTime = null", DeliveryRouting.class);
            query.setParameter("id", deliveryId);
            DeliveryRouting deliveryRouting = query.getSingleResult();
            if (deliveryRouting != null) {
                deliveryRouting.setDepartureTime(departureTime);
                session.getTransaction().commit();
            } else {
                throw new Exception("no delivery by this params");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isSuccess = false;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return isSuccess;
    }

    public DeliveryRouting saveDeliveryRouting(DeliveryRouting deliveryRouting) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(deliveryRouting);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return deliveryRouting;
    }
}
