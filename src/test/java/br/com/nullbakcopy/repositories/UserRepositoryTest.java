package br.com.nullbakcopy.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nullbakcopy.models.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		User user1 = new User();
		user1.setCpf("12675872377");
		user1.setName("TESTE");
        user1.setDhCreation(LocalDateTime.now(ZoneId.of("UTC-3")));
        user1.setDhModification(LocalDateTime.now(ZoneId.of("UTC-3")));
        
        User user2 = new User();
        user2.setCpf("75258058220");
        user2.setName("TESTE");
        user2.setDhCreation(LocalDateTime.now(ZoneId.of("UTC-3")));
        user2.setDhModification(LocalDateTime.now(ZoneId.of("UTC-3")));
	
        this.userRepository.save(user1);
        this.userRepository.save(user2);
	}
	
	@Test
	public void testFindAll_ok() {
		List<User> lista = this.userRepository.findAll();
		assertFalse(lista.isEmpty());
	}
	
	
}
