package com.test.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void toJSON() {
        Delivery delivery = new Delivery();
        assertEquals("", delivery.toJSON());
    }
}