package tw.yayichen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController {

	@GetMapping(path = "/member/{mid}", produces = "text/plain;charset=UTF-8")
	public String processPathVariableAction(@PathVariable("mid") String memberId) {
		return memberId;
	}
}
