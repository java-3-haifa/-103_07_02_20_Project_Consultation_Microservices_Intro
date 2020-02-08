package com.telran.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ContactsApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	public Optional<URI> serviceUrl(String id) {
		return discoveryClient.getInstances(id)
				.stream()
				.map(si -> si.getUri())
				.findFirst();
	}

	@GetMapping("/")
	public String hello(){
		return "Hello from contacts";
	}

	@GetMapping("/all")
	public String getAll() throws ServiceUnavailableException {
		Optional<URI> otp = serviceUrl("users");
		System.out.println(otp);

		URI	uri = otp.map(s -> s.resolve("/userid"))
				.orElseThrow(ServiceUnavailableException::new);

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForEntity(uri, String.class)
				.getBody();
	}
	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

}
