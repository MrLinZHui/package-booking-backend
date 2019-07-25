package com.oocl.packagebooking.respository;

import com.oocl.packagebooking.Entity.Erder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Erder,Long> {
}
