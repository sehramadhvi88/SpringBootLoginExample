package com.learningjavaspring.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learningjavaspring.repository.RoleRepository;
import com.learningjavaspring.repository.UserRepository;
import com.learningtechspring.model.Role;
import com.learningtechspring.model.User;

@Service("userService")
public class UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public User findUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public void saveUser(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		Set userSet = user.getRoles();
		userSet.add(userRole);
		user.setRoles(userSet);
		userRepository.save(user);
	}
	
}
