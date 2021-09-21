package tw.yayichen.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BirdService {
	
	@Autowired
	private BirdRepository birdRepository;
	
	public Bird insert(Bird bird) {
		return birdRepository.save(bird);
	}
	
	public Bird update(Bird bird) {
		return birdRepository.save(bird);
	}
	
	public void deleteById(Integer id) {
		birdRepository.deleteById(id);
	}
	
	public Bird findById(Integer id) {
		Optional<Bird> birdOptional = birdRepository.findById(id);
		return birdOptional.get();
	}
}
