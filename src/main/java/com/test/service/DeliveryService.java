package com.test.service;

import com.test.DAO.DeliveryDAO;
import com.test.entity.Delivery;
import com.test.entity.PostOffice;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryDAO deliveryDAO;

    public DeliveryService(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    public Delivery getDelivery(int deliveryId) {
        return deliveryDAO.getDelivery(deliveryId);
    }

    public Delivery getDeliveryWithHistory(int deliveryId) {
        return deliveryDAO.getDeliveryWithHistory(deliveryId);
    }

    public Delivery saveDelivery(Delivery delivery, PostOffice postOffice) {
        return deliveryDAO.saveDelivery(delivery, postOffice);
    }

    public boolean setDeliveryStatus(Delivery delivery, boolean isComplete) {
        return deliveryDAO.setDeliveryStatus(delivery, isComplete);
    }
}
