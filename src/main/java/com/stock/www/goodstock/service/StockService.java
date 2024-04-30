package com.stock.www.goodstock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockService {
    private final RestTemplate restTemplate;

    public String getStockHtml(int page){
        int parsingStart = 0;
        int parsingEnd = 0;
        List<String> stockNumber = new ArrayList<String>();

        while (parsingStart != -1 || parsingEnd!=-1) {

            ResponseEntity<String> res = restTemplate.getForEntity("https://finance.naver.com/sise/sise_market_sum.nhn?sosok=0&page="+page++, String.class);

            String nextStr = "";
            nextStr = res.getBody();
            parsingStart = nextStr.indexOf("<a href=\"/item/main.naver?code=");
            parsingEnd = nextStr.indexOf("class=\"tltle\">");
            if (parsingStart == -1 || parsingEnd == -1) {
                break;
            }
            while (parsingStart != -1 && parsingEnd != -1) {
                parsingStart = nextStr.indexOf("<a href=\"/item/main.naver?code=");
                parsingEnd = nextStr.indexOf("class=\"tltle\">");
                //System.out.println(parsingStart + "///" + parsingEnd);
                if (parsingStart == -1 || parsingEnd == -1) {
                    break;
                }
                String stockNumStr = nextStr.substring(parsingStart + 31, parsingEnd - 2);
                //System.out.println("targetStr=" + stockNumStr);
                stockNumber.add(stockNumStr);
                nextStr = nextStr.substring(parsingEnd + 13);
                //System.out.println("nextStr="+nextStr);
            }
            parsingStart = 0;
            parsingEnd = 0;
        }

        System.out.println("size="+stockNumber.size());
        // code 저장
        // https://finance.naver.com/item/coinfo.naver?code=005930&target=finsum_more

        String stockInfoResult = getStockInfoResultByStockNum(stockNumber);

        return stockInfoResult;
    }

    public String getStockInfoResultByStockNum(List<String> stockNumer) {

        for(String code:stockNumer){
            System.out.println(code);
            ResponseEntity<String> res = restTemplate.getForEntity("https://finance.naver.com/item/coinfo.naver?code="+code+"&target=finsum_more", String.class);
            System.out.println("result="+res.getBody());
        }

        return "";
    }


}
