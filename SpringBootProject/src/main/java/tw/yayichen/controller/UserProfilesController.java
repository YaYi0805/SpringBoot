package tw.yayichen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.yayichen.model.UserProfiles;
import tw.yayichen.model.UserProfilesService;

@RestController
public class UserProfilesController {
	
	@Autowired
	private UserProfilesService uService;

	@PostMapping(path = "/createuser.controller")
    public UserProfiles processCreateUserAction(@RequestBody UserProfiles uProfiles) {
		String bcEncode1 = new BCryptPasswordEncoder().encode(uProfiles.getPassword());
		uProfiles.setPassword(bcEncode1);		
		return uService.createUserProfiles(uProfiles);
    }
}
