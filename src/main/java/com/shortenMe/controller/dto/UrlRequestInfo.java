package com.shortenMe.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UrlRequestInfo {
	private long hits;
	private ArrayList<String> hitDate;
	private LocalDate creationDate;
	private String id;
	private String shortenedUrl;
	private String url;

	public void increaseHit() {
		hits++;
		hitDate.add(LocalDateTime.now().toLocalDate().toString());
	}

	public UrlRequestInfo(String id, String shortenedUrl, String url, LocalDate creationDate) {
		this.id = id;
		this.shortenedUrl = shortenedUrl;
		this.url = url;
		this.creationDate = creationDate;
		hits = 0;
		hitDate = new ArrayList<String>();

	}

	public String getId() {
		return id;
	}

	public String getCreationDate() {
		return creationDate.toString();
	}

	public String toString() {
		return "id: " + id + ", hits: " + hits  + ", creationDate: " + creationDate;

	}
	

}
