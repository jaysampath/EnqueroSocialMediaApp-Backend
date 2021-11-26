package com.enquero.EnqueroSocialMediaApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageRestController {

	@GetMapping("/")
	public String showHomePage() {
		String home = "<h1> Enquero's SocialMediaApp Restful Web Services using MongoDB</h1>" + " <h2> Welcome </h2>";

		return home;
	}

}
