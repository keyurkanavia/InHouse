package com.oe.RetailDashAPIServices;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RetailDashServices {

	public static void main(String []args){
		callAPI();
	}
	
	
	public static void callAPI(){
		try {

			Client client = Client.create();

			WebResource resource = Client.create(new DefaultClientConfig()).resource("http://52.9.31.59/api/v1/retailerProductConfigurations");

			WebResource.Builder builder = resource.accept(MediaType.APPLICATION_JSON);
			builder.type(MediaType.APPLICATION_JSON);
			builder.header("app-Id", "lcJ6wXeUQTi9ick8").header("api-Key", "rEwMu8Otd4wDRC7IKGGhhUvLOWgYxX9j");

			ClientResponse response = builder.head();
			
			System.out.println("Output from Server .... \n");
			System.out.println(response.getHeaders());

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	}
}
