package com.test.service;

import com.test.DAO.DeliveryDAO;
import com.test.entity.Delivery;
import com.test.entity.DeliveryRouting;
import com.test.entity.PostOffice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {
    @InjectMocks
    private DeliveryService deliveryService;
    @Mock
    private DeliveryDAO deliveryDAO;


    @Test
    void getDelivery() {
        Delivery delivery = getDeliveryMock();
        Mockito.when(deliveryDAO.getDelivery(10)).thenReturn(delivery);
        Assertions.assertEquals(delivery, deliveryService.getDelivery(10));
    }

    @Test
    void getDeliveryWithHistory() {
        Delivery delivery = getDeliveryMockWithHistory();
        Mockito.when(deliveryDAO.getDeliveryWithHistory(10)).thenReturn(delivery);
        Assertions.assertEquals(delivery, deliveryService.getDeliveryWithHistory(10));
    }

    @Test
    void saveDelivery() {
        Delivery delivery = new Delivery();
        PostOffice postOffice = new PostOffice();
        deliveryService.saveDelivery(delivery, postOffice);
        Mockito.verify(deliveryDAO, Mockito.times(1))
                .saveDelivery(delivery, postOffice);
    }

    @Test
    void setDeliveryStatus() {
        Delivery delivery = new Delivery();
        deliveryService.setDeliveryStatus(delivery, true);
        Mockito.verify(deliveryDAO, Mockito.times(1))
                .setDeliveryStatus(delivery, true);
    }

    private static Delivery getDeliveryMock() {
        Delivery delivery = new Delivery("mail", 15,
                "Moscow", "Ivan");
        delivery.setDeliveryId(10);
        return delivery;
    }

    private static Delivery getDeliveryMockWithHistory() {
        Delivery delivery = getDeliveryMock();
        DeliveryRouting deliveryRouting1 =
                new DeliveryRouting(delivery,
                        new PostOffice(3, "dhl", "Rostov"),
                        LocalDateTime.parse("2022-12-03T10:15:30"));
        DeliveryRouting deliveryRouting2 =
                new DeliveryRouting(delivery,
                        new PostOffice(2, "sdek", "Moskow"),
                        LocalDateTime.parse("2023-01-03T10:15:30"));
        delivery.setDeliveryRoutingList(List.of(deliveryRouting1, deliveryRouting2));
        return delivery;
    }
}