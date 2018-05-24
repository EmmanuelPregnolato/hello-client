/**
 * 
 */
package com.techprimers.cloud.helloclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * @author emmanuelpregnolato
 *
 */
@RestController
@RequestMapping("/rest/hello/client")
public class HelloResource {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping
	public String hello(){
		
		String url = "http://eureka-client/rest/hello/server";
		//String url = "http://localhost:8081/rest/hello/server";
		return restTemplate.getForObject(url, String.class);
	}
	
	public String fallback(Throwable hystrixCommand) {
		
		System.out.println(hystrixCommand.getMessage());
		return "Fallback hello world!";
	}
}
