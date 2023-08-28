package com.accenture.higherorlower.service.impl;

import com.accenture.higherorlower.model.Player;
import com.accenture.higherorlower.repository.PlayerRepository;
import com.accenture.higherorlower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

	private PlayerRepository playerRepository;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(PlayerRepository playerRepository, BCryptPasswordEncoder passwordEncoder) {
		this.playerRepository = playerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Player player = playerRepository.findByName(username);
		if (player == null) {
			throw new UsernameNotFoundException("User not found");
		}
		//return player;
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority("player");
		authorities.add(tempAuthority);
		return new org.springframework.security.core.userdetails.User(player.getName(), player.getPassword(),
				authorities);
	}
}
