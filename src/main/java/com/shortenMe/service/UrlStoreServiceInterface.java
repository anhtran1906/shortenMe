package com.shortenMe.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlStoreServiceInterface {
    String findUrlById(String id);
    void storeUrl(String id, String url);
}
