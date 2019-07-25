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
        erder.setOrderstatus("未预约");
        Erder erder1 = orderRespository.save(erder);

        return erder1;
    }

    public  List<Erder> putOrders(Erder erder) {
        Erder erder1 = orderRespository.findByOrderid(erder.getOrderid());
        erder1.setOrderstatus("已预约");
        erder1.setOrdertime(erder.getOrdertime());
        erder1 = orderRespository.save(erder1);
        return orderRespository.findAll();
    }

    public List<Erder> finishedOrders(String id, String orderstatus) {
        Erder erder = orderRespository.findByOrderid(id);
        erder.setOrderstatus("已取件");
        orderRespository.save(erder);
        return orderRespository.findAll();
    }

    public List<Erder> getOrdersByStatus(String status) {
        return orderRespository.findAllByOrderstatus(status);
    }
}
