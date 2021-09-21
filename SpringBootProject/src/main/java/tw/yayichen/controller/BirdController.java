package tw.yayichen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.yayichen.model.Bird;
import tw.yayichen.model.BirdService;

@RestController
public class BirdController {

	@Autowired
	private BirdService birdService;
	
	@GetMapping(path = "/birdinsert.controller")
	//預設:@GetMapping(path = "/birdinsert.controller", produces = "application/json")
	public Bird processInsertAction() {
		Bird bird = new Bird();
		bird.setBname("Eagle");
		bird.setColor("Brown");
		bird.setSize("L");
		bird.setAge(10);
		return birdService.insert(bird);
	}
	
	@GetMapping(path = "/users/birdquery.controller")
	public Bird processQueryAction() {
		return birdService.findById(100);
	}
	
	@GetMapping(path = "/birdupdate.controller")
	public Bird processUpdateAction() {
		Bird bird2 = new Bird();
		bird2.setBid(100);
		bird2.setBname("Eagle");
		bird2.setColor("Pink");
		bird2.setSize("M");
		bird2.setAge(18);
		return birdService.update(bird2);
	}
	
	@GetMapping(path = "/birddelete.controller")
	public String processDeleteAction() {
		birdService.deleteById(101);
		return "OK";
	}
}
