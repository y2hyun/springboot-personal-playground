package com.y2hyun.playground.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.y2hyun.playground.domain.User;
import com.y2hyun.playground.repository.UserRepository;
import com.y2hyun.playground.service.UserService;
import com.y2hyun.playground.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserDetailsService userDetailsService;
	
	@Override
	public void registUser(UserVo user) {
 
		User userEntity = User.builder()
			.name(user.getName())
			.password(this.passwordEncoder.encode(user.getPassword()))
			.email(user.getEmail())
			.createDate(LocalDateTime.now()).build();
			
		this.userRepository.save(userEntity);
	}

	@Override
	public void loginAfterRegistration(String email, String password) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptySet());
		
		SecurityContextHolder.getContext().setAuthentication(token);
		log.debug("{} login after registration.", email);
	}

	
}
