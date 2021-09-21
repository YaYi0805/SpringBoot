package tw.yayichen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.yayichen.model.Person;

@RestController
public class PersonController {

	@Autowired
	private Person person;
	
	@GetMapping(path = "/person.controller") //@GetMapping: @RequestMapping + method:GET
	public Person processAction() {
		return person;
	}
	
}
