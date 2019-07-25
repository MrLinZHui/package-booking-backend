package com.oocl.packagebooking.respository;

import com.oocl.packagebooking.Entity.Erder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<Erder,Long> {
    Erder findByOrderid(String orderid);

    List<Erder> findAllByOrderstatus(String orderstatus);
}
