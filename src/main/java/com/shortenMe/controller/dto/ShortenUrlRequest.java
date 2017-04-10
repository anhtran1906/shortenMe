package com.shortenMe.controller.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ShortenUrlRequest {
	private long hits;
	private LocalDateTime creationDate;
    @NotNull
    @Size(min = 5, max = 1024)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    private void setHits(Long hits) {
        this.hits = hits;
    }
    
    public void increaseHits(){
        this.hits += 1;
    }
    
    
}
