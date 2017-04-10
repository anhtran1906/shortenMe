package com.shortenMe.service;


public interface UrlStoreServiceInterface {
    String findUrlById(String id);
    void storeUrl(String id, String url);
}
