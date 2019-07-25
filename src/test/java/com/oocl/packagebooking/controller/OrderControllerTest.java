package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.Entity.Erder;
import com.oocl.packagebooking.respository.OrderRespository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    OrderRespository orderRespository;
    @Before
    public void DeletteAll(){
        orderRespository.deleteAll();
    }
    @Test
    public void should_get_orders_when_give_all_orders()throws Exception{
        //given
        Erder erder = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
        orderRespository.save(erder);
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/orders")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        //JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals("20190725", jsonArray.getJSONObject(0).getString("orderid"));
        assertEquals("张三", jsonArray.getJSONObject(0).getString("receviname"));
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
    @Test
    public void should_return_a_new_order_when_put_a_new_time()throws Exception{
        //given
        Erder erder1 = new Erder("20190725","张三","13192269629","","");
        Erder erder = new Erder("20190725","","","","20190725 09:35:13");
        orderRespository.save(erder1);
        //when
        MvcResult mvcResult = this.mockMvc.perform(put("/orders").content(new ObjectMapper().writeValueAsString(erder))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals("已预约", jsonObject.getString("orderstatus"));
        assertEquals("20190725 09:35:13", jsonObject.getString("ordertime"));
    }
    @Test
    public void should_return_a_new_order_when_put_finish()throws Exception{
        //given
        Erder erder1 = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
        orderRespository.save(erder1);
        String status = "已取件";
        //when
        MvcResult mvcResult = this.mockMvc.perform(put("/orders/20190725").content(new ObjectMapper().writeValueAsString(status))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals("已取件", jsonArray.getJSONObject(0).getString("orderstatus"));
    }
    @Test
    public void should_return_a_1_when_get_a_status_isbooking()throws Exception{
        //given
        Erder erder1 = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
        Erder erder2 = new Erder("20190726","李四","13192269","已取件","20190726 09:35:13");
        Erder erder3 = new Erder("20190727","王五","13192269","未预约","20190727 09:35:13");
        List<Erder> erderList = Arrays.asList(erder1,erder2,erder3);
        orderRespository.saveAll(erderList);
        String status = "已取件";
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/orders?status=已预约")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals(1, jsonArray.length());
    }
    @Test
    public void should_return_a_1_when_get_a_status_getOrder()throws Exception{
        //given
        Erder erder1 = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
        Erder erder2 = new Erder("20190726","李四","13192269","已取件","20190726 09:35:13");
        Erder erder3 = new Erder("20190727","王五","13192269","未预约","20190727 09:35:13");
        List<Erder> erderList = Arrays.asList(erder1,erder2,erder3);
        orderRespository.saveAll(erderList);
        String status = "已取件";
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/orders?status=已取件")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals(1, jsonArray.length());
        // assertEquals("20190725 09:35:13", jsonObject.getString("ordertime"));
    }
    @Test
    public void should_return_a_1_when_get_a_status_notBooking()throws Exception{
        //given
        Erder erder1 = new Erder("20190725","张三","13192269629","已预约","20190725 09:35:13");
        Erder erder2 = new Erder("20190726","李四","13192269","已取件","20190726 09:35:13");
        Erder erder3 = new Erder("20190727","王五","13192269","未预约","20190727 09:35:13");
        List<Erder> erderList = Arrays.asList(erder1,erder2,erder3);
        orderRespository.saveAll(erderList);
        String status = "已取件";
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/orders?status=未预约")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals(1, jsonArray.length());
        // assertEquals("20190725 09:35:13", jsonObject.getString("ordertime"));
    }
}
