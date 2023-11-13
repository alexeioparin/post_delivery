package com.test.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "mail", name = "post_offices")
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_office_id")
    private int postOfficeId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "postOffice")
    private List<DeliveryRouting> deliveryRoutingList;

    public PostOffice() {
    }

    public PostOffice(int postOfficeId, String name, String address) {
        this.postOfficeId = postOfficeId;
        this.name = name;
        this.address = address;
    }

    public int getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(int postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DeliveryRouting> getDeliveryRoutingList() {
        return deliveryRoutingList;
    }

    public void setDeliveryRoutingList(List<DeliveryRouting> deliveryRoutingList) {
        this.deliveryRoutingList = deliveryRoutingList;
    }
}
