package com.test.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.test.pojo.Result;
import com.test.pojo.Result;
import com.test.entity.Delivery;
import com.test.entity.DeliveryRouting;
import com.test.entity.PostOffice;
import com.test.service.DeliveryService;
import com.test.service.PostOfficeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class DeliveryController {
    private final ObjectMapper mapper = new ObjectMapper();
    private final DeliveryService deliveryService;
    private final PostOfficeService postOfficeService;

    public DeliveryController(DeliveryService deliveryService, PostOfficeService postOfficeService) {
        this.deliveryService = deliveryService;
        this.postOfficeService = postOfficeService;
    }


    @PostMapping(value = "/new-delivery", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createDelivery(@RequestBody String request) {
        Result result;

        try {
            Delivery delivery = mapper.readValue(request, Delivery.class);
            JsonNode node = mapper.readTree(request);
            int postOfficeId = node == null ? 0 : node.get("postOfficeId").asInt(0);
            PostOffice postOffice = postOfficeService.getPostOffice(postOfficeId);
            if (postOffice == null) {
                return new Result(false, "", "wrong post office id number").toJSON();
            }

            delivery = deliveryService.saveDelivery(delivery, postOffice);
            if (delivery.getDeliveryId() != 0) {
                result = new Result(true, mapper.writeValueAsString(delivery), "");
            } else {
                result = new Result(false, "", "DB save error");
            }

        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            result = new Result(false, "", "wrong json format");
        }

        return result.toJSON();
    }

    @GetMapping("/tracking/{id}")
    public String getDeliveryHistory(@PathVariable(value = "id") int deliveryId) {
        Result result;

        Delivery delivery = deliveryService.getDeliveryWithHistory(deliveryId);
        if (delivery != null) {
            StringBuilder deliveryHistory = new StringBuilder("[");
            for (DeliveryRouting deliveryRouting : delivery.getDeliveryRoutingList()) {
                deliveryHistory.append(deliveryRouting.toJSON()).append(",");
            }
            deliveryHistory.deleteCharAt(deliveryHistory.length() - 1).append("]");
            result = new Result(true, deliveryHistory.toString(), "");
        } else {
            result = new Result(false, "", "wrong delivery id");
        }

        return result.toJSON();
    }
}
