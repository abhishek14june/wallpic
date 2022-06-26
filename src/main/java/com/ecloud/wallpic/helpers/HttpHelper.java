package com.ecloud.wallpic.helpers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class HttpHelper {

	

	
	public JSONArray callForJsonArrayResponse(String requestUrl) {
		JSONArray response = null;
		try {
			RestTemplate restTemplate = getRestTemplate();
			String stringResponse = restTemplate.getForObject(requestUrl, String.class);
			response = new JSONArray(stringResponse);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private RestTemplate getRestTemplate() {


		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "PostmanRuntime/7.29.0");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return restTemplate;
	}

	public JSONObject callForJsonObjectResponse(String requestUrl) {
		JSONObject response = null;
		try {

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent", "PostmanRuntime/7.27.0");
			headers.add("X-Forwarded-For","127.0.0.1");
			headers.add("X-Forwarded-For","127.0.0.1");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> stringResponse = restTemplate.exchange(requestUrl, HttpMethod.GET,entity,String.class);
			response = new JSONObject(stringResponse.getBody());
		}
		catch(Exception e) {

			//e.printStackTrace();
		}
		return response;
	}
}
