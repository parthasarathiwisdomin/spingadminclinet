package info.wisdomin.springclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Value("${welcome.message}")
	private String welcomeMessage  ;

	@GetMapping("/")
	public String retrieveWelcomeMessage() {
		
		return welcomeMessage;
	}
}
