package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.Entity.Erder;
import com.oocl.packagebooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@CrossOrigin(value = "*")
@RequestMapping(path = "/orders")
public class OrderController {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity getOrders(@RequestParam(required = false)String  status){
        return ResponseEntity.ok(orderService.getOrders());
    }
    @GetMapping(params = {"status"})
    public ResponseEntity getOrdersBystatus(@RequestParam(required = false)String  status){

            return ResponseEntity.ok(orderService.getOrdersByStatus(status));

    }

    @PostMapping
    public ResponseEntity postOrders(@RequestBody Erder erder){
        return ResponseEntity.ok(orderService.postOrders(erder));
    }

    @PutMapping
    public ResponseEntity putOrders(@RequestBody Erder erder){
        System.out.println("====="+erder.getOrderid()+","+erder.getOrdertime());
        return ResponseEntity.ok(orderService.putOrders(erder));
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity finishedOrders(@PathVariable String id,@RequestBody String orderstatus){
        System.out.println("======="+orderstatus);
        return ResponseEntity.ok(orderService.finishedOrders(id,orderstatus));
    }
}
