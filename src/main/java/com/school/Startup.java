package com.school;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);

		Map<String, PasswordEncoder> encoder = new HashMap<>();

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		encoder.put("bcrypt", bCryptPasswordEncoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoder);
		passwordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);

		var result = passwordEncoder.encode("teste");
		System.out.println(result);
	}

}
