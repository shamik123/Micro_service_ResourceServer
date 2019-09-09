package com.sinol.oauth.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("public")
public class PublicController {
	
	@Autowired
    RestTemplate restTemplate;
 
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

	@RequestMapping("/welcome")
	public @ResponseBody String getGreeting() {
		
		String response = (String) restTemplate.exchange("http://AuthServer/check_token",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>(){}).getBody();
		
		return response;
	}

}