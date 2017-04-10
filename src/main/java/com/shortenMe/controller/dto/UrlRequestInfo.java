package com.shortenMe.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

public class UrlRequestInfo {
	private long hits;
	private HashMap<String,Long> hitDate;
	private HashMap<String,HashMap<String,Long>> idHitDate;
	private LocalDate creationDate;
	private String id;


	public void increaseHit() {
		hits++;
		hitDate.put(LocalDateTime.now().toLocalDate().toString(),hits);
		idHitDate.put(id, hitDate);
	}

	public UrlRequestInfo(String id, LocalDate creationDate) {
		this.id = id;
		this.creationDate = creationDate;
		hits = 0;
		hitDate = new HashMap<String,Long>();
		idHitDate = new HashMap<String,HashMap<String,Long>>();

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
	public String printHitsDateMap(String id){
		String list = "";
		Set<String> dates =  idHitDate.get(id).keySet();
		for(String date: dates){
			list += "hit date: " + date + ", hits: " + idHitDate.get(id).get(date) + "\n";
		}
		return list;
	}
	

}
