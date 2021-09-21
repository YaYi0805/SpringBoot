package tw.yayichen.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRespository extends JpaRepository<Users, Integer> {

	@Query(value = "from Users where username like concat('%',?1,'%')")
	public List<Users> findUsers(String username);
	
	public List<Users> findByUsernameLike(String username);
	
	@Query(value = "Select * from Users", nativeQuery = true)
	public List<Users> findAll();
	
}
