package com.test.service;

import com.test.DAO.DeliveryRoutingDAO;
import com.test.entity.DeliveryRouting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DeliveryRoutingServiceTest {
    @InjectMocks
    DeliveryRoutingService deliveryRoutingService;
    @Mock
    DeliveryRoutingDAO deliveryRoutingDAO;

    @Test
    void setDeliveryDepartureTime() {
        LocalDateTime ldt = LocalDateTime.now();
        deliveryRoutingService.setDeliveryDepartureTime(1, ldt);
        Mockito.verify(deliveryRoutingDAO, Mockito.times(1))
                .setDeliveryDepartureTime(1, ldt);
    }

    @Test
    void saveDeliveryRouting() {
        DeliveryRouting deliveryRouting = new DeliveryRouting();
        deliveryRoutingService.saveDeliveryRouting(deliveryRouting);
        Mockito.verify(deliveryRoutingDAO, Mockito.times(1))
                .saveDeliveryRouting(deliveryRouting);
    }
}