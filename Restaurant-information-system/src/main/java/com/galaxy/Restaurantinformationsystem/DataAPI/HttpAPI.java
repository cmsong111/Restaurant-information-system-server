package com.galaxy.Restaurantinformationsystem.DataAPI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class HttpAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${StoreKidsServiceKey}")
    String StoreKidsServiceKey;

//    @Value("${StoreTastyServiceKey}")
//    String StoreTastyServiceKey;

    public String getKidsStore(Integer perPage, Integer page) throws IOException, URISyntaxException {
        HttpClient Client = HttpClientBuilder.create().build();

        // 파라미터
        String baseURL = "https://api.odcloud.kr/api/15102055/v1/uddi:122cc22d-cde3-41d0-83c8-db83298b994f";


        // URL 생성
        HttpGet httpget = new HttpGet(baseURL);
        URI uri = new URIBuilder(httpget.getURI())
                .addParameter("serviceKey", StoreKidsServiceKey)
                .addParameter("perPage", perPage.toString())
                .addParameter("page", page.toString())
                .build();
        httpget.setURI(uri);

        // HTTP GET method 실행
        HttpResponse response = Client.execute(httpget);

        // body 결과값 얻기
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        // 로그 남기기
        logger.info(httpget.getURI().toString());
        logger.info(StoreKidsServiceKey);

        return result;
    }

    public String getTastyStore(Integer perPage, Integer page) throws URISyntaxException, IOException {
        HttpClient Client = HttpClientBuilder.create().build();

        // 파라미터
        String baseURL = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";


        // URL 생성
        HttpGet httpget = new HttpGet(baseURL);
        URI uri = new URIBuilder(httpget.getURI())
                .addParameter("serviceKey", StoreKidsServiceKey)
                .addParameter("numOfRows", perPage.toString())
                .addParameter("pageNo", page.toString())
                .addParameter("resultType", "json")
                .build();
        httpget.setURI(uri);
        logger.info(httpget.getURI().toString());

        // HTTP GET method 실행
        HttpResponse response = Client.execute(httpget);

        // body 결과값 얻기
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        // 로그 남기기
        logger.info(httpget.getURI().toString());
        logger.info(StoreKidsServiceKey);

        return result;
    }

    public String getGoodPriceStore(Integer perPage, Integer page) throws URISyntaxException, IOException {
        HttpClient Client = HttpClientBuilder.create().build();

        // 파라미터
        String baseURL = "http://apis.data.go.kr/6260000/GoodPriceStoreService/getGoodPriceStore";

        // URL 생성
        HttpGet httpget = new HttpGet(baseURL);
        URI uri = new URIBuilder(httpget.getURI())
                .addParameter("ServiceKey", StoreKidsServiceKey)
                .addParameter("numOfRows", perPage.toString())
                .addParameter("pageNo", page.toString())
                .addParameter("resultType", "json")
                .build();
        httpget.setURI(uri);
        httpget.setHeader("Content-type", "application/json;charset=UTF-8");
        logger.info(httpget.getURI().toString());

        // HTTP GET method 실행
        HttpResponse response = Client.execute(httpget);

        // body 결과값 얻기
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity,"UTF-8");

        //로그 남기기
        logger.info(httpget.getURI().toString());
        logger.info(StoreKidsServiceKey);
        //System.out.println(result);
        return result;
    }
}
