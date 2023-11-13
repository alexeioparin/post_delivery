package com.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "mail", name = "deliveries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private int deliveryId;
    @Column(name = "type")
    private String type;
    @Column(name = "addressee_id")
    private int addresseeId;
    @Column(name = "addressee_point")
    private String addresseePoint;
    @Column(name = "addressee_name")
    private String addresseeName;
    @Column(name = "is_received")
    private boolean isReceived;
    @OneToMany(mappedBy = "delivery")
    private List<DeliveryRouting> deliveryRoutingList;

    public Delivery() {}

    public Delivery(String type, int addresseeId, String addresseePoint, String addresseeName) {
        this.type = type;
        this.addresseeId = addresseeId;
        this.addresseePoint = addresseePoint;
        this.addresseeName = addresseeName;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAddresseeId() {
        return addresseeId;
    }

    public void setAddresseeId(int addresseeId) {
        this.addresseeId = addresseeId;
    }

    public String getAddresseePoint() {
        return addresseePoint;
    }

    public void setAddresseePoint(String addresseePoint) {
        this.addresseePoint = addresseePoint;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    public List<DeliveryRouting> getDeliveryRoutingList() {
        return deliveryRoutingList;
    }

    public void setDeliveryRoutingList(List<DeliveryRouting> deliveryRoutingList) {
        this.deliveryRoutingList = deliveryRoutingList;
    }


    public String toJSON() {
        return "{\"deliveryId\":" + deliveryId + ",\"type\":\"" + type +
                "\",\"addresseeId\":" + addresseeId + ",\"addresseePoint\":\"" +
                addresseePoint + "\",\"addresseeName\":\"" + addresseeName +
                "\",\"isReceived\":" + isReceived + "}";
    }
}
