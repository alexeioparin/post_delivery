package com.test.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(schema = "mail", name = "delivery_routing")
public class DeliveryRouting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_routing_id")
    private int deliveryRoutingId;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    @ManyToOne
    @JoinColumn(name = "post_office_id")
    private PostOffice postOffice;


    public DeliveryRouting() {}

    public DeliveryRouting(Delivery delivery, PostOffice postOffice, LocalDateTime arrivalTime) {
        this.delivery = delivery;
        this.postOffice = postOffice;
        this.arrivalTime = arrivalTime;
    }

    public int getDeliveryRoutingId() {
        return deliveryRoutingId;
    }

    public void setDeliveryRoutingId(int deliveryRoutingId) {
        this.deliveryRoutingId = deliveryRoutingId;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }


    public String toJSON() {
        return "{\"postOfficeId\":" + postOffice.getPostOfficeId() + ",\"arrivalTime\":\"" +
                arrivalTime + "\",\"departureTime\":\"" + departureTime + "\"}";
    }
}
