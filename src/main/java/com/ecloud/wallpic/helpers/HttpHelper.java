package com.ecloud.wallpic.helpers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpHelper {

	
	@Autowired
	RestTemplate restTemplate;
	
	public JSONArray callForJsonArrayResponse(String requestUrl) {
		JSONArray response = null;
		try {
			String stringResponse = restTemplate.getForObject(requestUrl, String.class);
			response = new JSONArray(stringResponse);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public JSONObject callForJsonObjectResponse(String requestUrl) {
		JSONObject response = null;
		try {
			String stringResponse = restTemplate.getForObject(requestUrl, String.class);
			response = new JSONObject(stringResponse);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
