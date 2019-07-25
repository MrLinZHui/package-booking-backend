package com.oocl.packagebooking.service;

import com.oocl.packagebooking.Entity.Erder;
import com.oocl.packagebooking.respository.OrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRespository orderRespository;
    public List<Erder>  getOrders() {
        return orderRespository.findAll();
    }

    public Erder postOrders(Erder erder) {
        Erder erder1 = orderRespository.save(erder);
        return erder;
    }
}
