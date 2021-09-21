package tw.yayichen.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UsersRespository usersRespository;
	
	public List<Users> findUsers(String username){
		return usersRespository.findUsers(username);
	}
	
	public List<Users> findByUsernameLike(String username){
		return usersRespository.findByUsernameLike(username);
	}

}
