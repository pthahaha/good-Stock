package com.stock.www.goodstock.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.stock.www.goodstock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

//    @GetMapping("/getStockInfo")
//    public ResponseEntity<String> getHello(@RequestParam int page) {
//        ResponseEntity<String> res = stockService.getStockHteml(page);
//        return res;
//    }
//    @GetMapping("/hello2")
//    public String getHello2(@RequestParam String q) {
//        String res = stockService.getStockHtml2(q);
//        return "test2:"+res;
//    }

    @GetMapping("/getStock")
    @ResponseBody
    public String getHello3(@RequestParam int page) {
        String res = stockService.getStockHtml(page);
        return res;
    }
}
