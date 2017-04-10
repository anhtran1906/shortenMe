package com.shortenMe;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shortenMe.controller.dto.UrlRequestInfo;

@SpringBootApplication
public class ShortenMeApplication {
    public static HashMap<String,UrlRequestInfo> infoMap = new HashMap<String,UrlRequestInfo>();

    public static void main(String[] args) {
    	
        SpringApplication.run(ShortenMeApplication.class, args);
        
    }
    public static HashMap<String,UrlRequestInfo> getInfoMap(){
    	return infoMap;
    }
}
