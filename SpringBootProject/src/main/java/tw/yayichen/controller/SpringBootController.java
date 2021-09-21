package tw.yayichen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //不要用@RestController因為要回傳的是jsp不是單純字串@ResponseBody
public class SpringBootController {

	@GetMapping(path = "/spaction.controller")
	//等同@RequestMapping(path = "/spaction.controller", method = RequestMethod.GET)
	public String processAction() {
		return "welcome";
	}
	
}
