package com.test.controller;

import com.test.DAO.DeliveryDAO;
import com.test.entity.Delivery;
import com.test.entity.DeliveryRouting;
import com.test.entity.PostOffice;
import com.test.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class DeliveryControllerTest {
    @InjectMocks
    private DeliveryService deliveryService;
    @Mock
    private DeliveryDAO deliveryDAO;

    @Test
    public void createDelivery() {

    }

    @Test
    public void getDeliveryHistory() {

    }


}