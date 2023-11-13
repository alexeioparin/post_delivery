package com.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pojo.Result;
import com.test.entity.Delivery;
import com.test.entity.DeliveryRouting;
import com.test.entity.PostOffice;
import com.test.service.DeliveryRoutingService;
import com.test.service.DeliveryService;
import com.test.service.PostOfficeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("api")
public class DeliveryRoutingController {
    private final ObjectMapper mapper = new ObjectMapper();
    private final DeliveryService deliveryService;
    private final DeliveryRoutingService deliveryRoutingService;
    private final PostOfficeService postOfficeService;

    public DeliveryRoutingController(DeliveryService deliveryService, DeliveryRoutingService deliveryRoutingService, PostOfficeService postOfficeService) {
        this.deliveryService = deliveryService;
        this.deliveryRoutingService = deliveryRoutingService;
        this.postOfficeService = postOfficeService;
    }


    @PostMapping(value = "/arrival", produces = MediaType.APPLICATION_JSON_VALUE)
    public String arriveToPostOffice(@RequestBody String request) {
        Result result;
        try {
            JsonNode node = mapper.readTree(request);
            if (node == null || node.isEmpty()) {
                return new Result(false, "", "no incoming data found").toJSON();
            }
            int deliveryId = node.get("deliveryId").asInt(0);
            Delivery delivery = deliveryService.getDeliveryWithHistory(deliveryId);
            if (delivery == null) {
                return new Result(false, "", "delivery not found").toJSON();
            }
            for (DeliveryRouting deliveryRouting: delivery.getDeliveryRoutingList()) {
                if (deliveryRouting.getDepartureTime() == null) {
                    return new Result(false, "", "delivery did not left last post office").toJSON();
                }
            }
            int postOfficeId = node.get("postOfficeId").asInt(0);
            PostOffice postOffice = postOfficeService.getPostOffice(postOfficeId);
            if (postOffice == null) {
                return new Result(false, "", "wrong post office id number").toJSON();
            }

            if (deliveryRoutingService.saveDeliveryRouting(new DeliveryRouting(delivery, postOffice,
                    node.get("arrivalTime") == null ? LocalDateTime.now() :
                            LocalDateTime.parse(node.get("arrivalTime").asText()))) != null) {
                result = new Result(true, "", "delivery arrived to post office");
            } else {
                result = new Result(false, "", "DB save error");
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            result = new Result(false, "", "wrong json format");
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            result = new Result(false, "", "wrong time format");
        }

        return result.toJSON();
    }

    @PostMapping(value = "/departure", produces = MediaType.APPLICATION_JSON_VALUE)
    public String leavePostOffice(@RequestBody String request) {
        Result result;
        try {
            JsonNode node = mapper.readTree(request);
            if (node == null || node.isEmpty()) {
                return new Result(false, "", "no incoming data found").toJSON();
            }
            int deliveryId = node.get("deliveryId").asInt(0);
            Delivery delivery = deliveryService.getDelivery(deliveryId);
            if (delivery == null) {
                return new Result(false, "", "delivery not found").toJSON();
            }
            if (deliveryRoutingService.setDeliveryDepartureTime(deliveryId,
                    node.get("departureTime") == null ? LocalDateTime.now() :
                            LocalDateTime.parse(node.get("departureTime").asText()))) {
                result = new Result(true, "", "delivery left post office");
            } else {
                result = new Result(false, "", "DB save error");
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            result = new Result(false, "", "wrong json format");
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            result = new Result(false, "", "wrong time format");
        }

        return result.toJSON();
    }

    @PostMapping(value = "/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deliveryComplete(@RequestBody String request) {
        Result result;
        try {
            JsonNode node = mapper.readTree(request);
            if (node == null || node.isEmpty()) {
                return new Result(false, "", "no incoming data found").toJSON();
            }
            int deliveryId = node.get("deliveryId").asInt(0);
            Delivery delivery = deliveryService.getDelivery(deliveryId);
            if (delivery == null) {
                return new Result(false, "", "delivery not found").toJSON();
            }
            if (deliveryService.setDeliveryStatus(delivery, true)) {
                if (leavePostOffice(delivery.toJSON()).contains("failure")) {
                    deliveryService.setDeliveryStatus(delivery, false);
                    result = new Result(false, "", "DB save error");
                } else {
                    result = new Result(true, "", "delivery complete");
                }
            } else {
                result = new Result(false, "", "DB save error");
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            result = new Result(false, "", "wrong json format");
        }

        return result.toJSON();
    }
}
