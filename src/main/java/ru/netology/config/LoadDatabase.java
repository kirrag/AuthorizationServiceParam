package ru.netology.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.netology.repository.UserRepository;
import ru.netology.authority.Authorities;
import java.util.Arrays;
import java.util.List;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	private static final List<Authorities> aclr = Arrays.asList(Authorities.valueOf("READ"));
	private static final List<Authorities> aclrw = Arrays.asList(Authorities.valueOf("READ"),
			Authorities.valueOf("WRITE"));
	private static final List<Authorities> aclrwd = Arrays.asList(Authorities.valueOf("READ"),
			Authorities.valueOf("WRITE"), Authorities.valueOf("DELETE"));

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(0, "ivan", "qwerty123", aclr).toString());

			log.info("Preloading " + repository.save(0, "oleg", "welcome1", aclrw).toString());

			log.info("Preloading " + repository.save(0, "masha", "Welcome1!", aclrwd).toString());
		};
	}
}
