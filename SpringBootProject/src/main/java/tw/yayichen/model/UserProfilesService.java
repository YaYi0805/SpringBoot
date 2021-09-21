package tw.yayichen.model;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.yayichen.exception.UserNotFoundException;

@Service
@Transactional
public class UserProfilesService {
	
	@Autowired
	private UserProfilesRespository userProfilesRespository;
	
	public UserProfiles getByName(String name) {
		Optional<UserProfiles> uResp = userProfilesRespository.findByName(name);
		
		if(uResp.isEmpty()) {
			throw new UserNotFoundException("Can't find User.");
		}
		
		return uResp.get();
	}
	
	public UserProfiles createUserProfiles(UserProfiles userProfiles) {
		return userProfilesRespository.save(userProfiles);
	}

}
