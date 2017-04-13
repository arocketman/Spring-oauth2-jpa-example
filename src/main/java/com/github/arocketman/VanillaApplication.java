package com.github.arocketman;

import com.github.arocketman.config.CustomUserDetails;
import com.github.arocketman.entities.Role;
import com.github.arocketman.entities.User;
import com.github.arocketman.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Arrays;


@SpringBootApplication
@EnableResourceServer
public class VanillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VanillaApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception {
		if (repository.count()==0)
			repository.save(new User("user", "password", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService(userDetailsService(repository));
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}




}
