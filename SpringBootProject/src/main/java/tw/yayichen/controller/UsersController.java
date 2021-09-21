package tw.yayichen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.yayichen.model.Users;
import tw.yayichen.model.UsersRespository;
import tw.yayichen.model.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping(path = "/users/findusers.controller")
	public List<Users> processFindUsersAction(){
		return usersService.findUsers("Ke");
	}
	
	@GetMapping(path = "/findbyusernamelike.controller")
	public List<Users> processFindByUsernameLikeAction(){
		String username = "ke";
		return usersService.findByUsernameLike("%"+ username +"%");
	}
	
	//也可直接使用UsersRespository介面
	@Autowired
	private UsersRespository usersRespository;
	
	@GetMapping(path = "/findall.controller")
	public List<Users> processFindAllAction(){
		return usersRespository.findAll();
	}
	
}
