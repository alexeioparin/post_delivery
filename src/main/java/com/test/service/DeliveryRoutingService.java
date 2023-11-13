package com.test.service;

import com.test.DAO.DeliveryRoutingDAO;
import com.test.entity.DeliveryRouting;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryRoutingService {
    private final DeliveryRoutingDAO deliveryRoutingDAO;

    public DeliveryRoutingService(DeliveryRoutingDAO deliveryRoutingDAO) {
        this.deliveryRoutingDAO = deliveryRoutingDAO;
    }

    public boolean setDeliveryDepartureTime(int deliveryId, LocalDateTime departureTime) {
        return deliveryRoutingDAO.setDeliveryDepartureTime(deliveryId, departureTime);
    }

    public DeliveryRouting saveDeliveryRouting(DeliveryRouting deliveryRouting) {
        return deliveryRoutingDAO.saveDeliveryRouting(deliveryRouting);
    }
}
