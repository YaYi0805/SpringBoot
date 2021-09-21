package tw.yayichen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.yayichen.model.PersonalProfiles;

@RestController
public class PersonalProfilesController {
	
	@Autowired
	private PersonalProfiles pp1;
	
	@GetMapping(path = "/personal.controller")
	public PersonalProfiles processAction() {
		return pp1;
	}
	
}
