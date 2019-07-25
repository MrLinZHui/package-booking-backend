package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.Entity.Erder;
import com.oocl.packagebooking.respository.OrderRespository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    OrderRespository orderRespository;
    @Test
    public void should_get_orders_when_give_all_orders()throws Exception{
        //given
        Erder erder = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
        orderRespository.save(erder);
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/orders")).andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals("20190725", jsonObject.getString("orderid"));
        assertEquals("张三", jsonObject.getString("receviname"));
    }
    @Test
    public void should_get_order_when_post_a_order()throws Exception{
        //given
        Erder erder = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
//        orderRespository.save(erder);
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/orders").content(new ObjectMapper().writeValueAsString(erder))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals("20190725", jsonObject.getString("orderid"));
        assertEquals("张三", jsonObject.getString("receviname"));
    }

}
