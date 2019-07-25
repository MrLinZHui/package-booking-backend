package com.oocl.packagebooking.Entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@DynamicInsert(true)
public class Erder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orderid;

    private String receviname;

    private String phone;

    private String orderstatus;

    private String ordertime;

    public Erder(String orderid, String receviname, String phone, String orderstatus, String ordertime) {
        this.orderid = orderid;
        this.receviname = receviname;
        this.phone = phone;
        this.orderstatus = orderstatus;
        this.ordertime = ordertime;
    }

    public Erder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getReceviname() {
        return receviname;
    }

    public void setReceviname(String receviname) {
        this.receviname = receviname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }
}
