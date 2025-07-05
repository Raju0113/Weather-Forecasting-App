/*
 * the purpose of this class is to configure the RestClient and create the bean in the spring ioc container
 * And also we customize the rest client using factory method for time out cases for that i set connection timeout and readtimeout limit
 * */
package com.raju.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	public RestClient restClient(RestClient.Builder builder) {

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(3000);  //Sets the maximum time (in milliseconds) to wait while connecting to a server.Here, it's 3000ms (i.e., 3 seconds). If the connection is not established in 3 seconds, it throws a timeout exception.
		factory.setReadTimeout(3000);     //Sets the maximum time to wait for a response after the connection is established.If the server takes more than 3 seconds to send data, it throws a timeout.

		return builder.requestFactory(factory).build();
		// return builder.build();
	}
}
