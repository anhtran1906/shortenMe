package com.shortenMe.controller;

import com.shortenMe.ShortenMeApplication;

import com.google.common.hash.Hashing;
import com.shortenMe.controller.dto.ShortenUrlRequest;
import com.shortenMe.controller.dto.UrlRequestInfo;
import com.shortenMe.service.UrlStoreServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class UrlController {
	@Autowired
	private UrlStoreServiceInterface urlStoreService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(ShortenUrlRequest request) {
		return "shortener";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void redirectToUrl(@PathVariable String id, HttpServletResponse resp) throws Exception {
		final String url = urlStoreService.findUrlById(id);
		if (url != null) {
			resp.addHeader("Location", url);
			resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);

		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView shortenUrl(HttpServletRequest httpRequest, @Valid ShortenUrlRequest request,
			BindingResult bindingResult) {
		String url = request.getUrl();
		if (!isUrlValid(url)) {
			bindingResult.addError(new ObjectError("url", "Invalid url format: " + url));
		}

		ModelAndView shortenedURLModelAndView = new ModelAndView("shortener");

		if (!bindingResult.hasErrors()) {
			final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
			urlStoreService.storeUrl(id, url);
			String requestUrl = httpRequest.getRequestURL().toString();
			String prefix = requestUrl.substring(0,
					requestUrl.indexOf(httpRequest.getRequestURI(), "http://".length()));
			shortenedURLModelAndView.addObject("shortenedUrl", prefix + "/" + id);

			
			UrlRequestInfo info = new UrlRequestInfo(id,LocalDateTime.now().toLocalDate());
			HashMap<String, UrlRequestInfo> infoMap = ShortenMeApplication.getInfoMap();

			if(infoMap.containsKey(id)){
				infoMap.get(id).increaseHit();
				shortenedURLModelAndView.addObject("infoMapObj", infoMap.get(id).toString());
				shortenedURLModelAndView.addObject("hitDates", infoMap.get(id).printHitsDateMap(id));
			}
			else {
				infoMap.put(id, info);
				
			}

		}
		return shortenedURLModelAndView;
	}

	private boolean isUrlValid(String url) {
		boolean valid = true;
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			valid = false;
		}
		return valid;
	}
}
