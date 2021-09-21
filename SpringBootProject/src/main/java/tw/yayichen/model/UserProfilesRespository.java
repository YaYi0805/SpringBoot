package tw.yayichen.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilesRespository extends JpaRepository<UserProfiles, String> {

	public Optional<UserProfiles> findByName(String name);
	
}
