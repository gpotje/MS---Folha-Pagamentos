//package com.example.config;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.example.domain.Const;
//import com.example.domain.Role;
//import com.example.domain.User;
//import com.example.repository.RoleRepository;
//import com.example.repository.UserRepository;
//
//@Component
//public class DataInitializr  implements ApplicationListener<ContextRefreshedEvent>{
//
//	@Autowired
//	UserRepository userRepository;
//	
//	@Autowired
//	RoleRepository roleRepository;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		List<User> users = userRepository.findAll();
//		
//		if(users.isEmpty()) {
//			createUser("Admin", "admin", passwordEncoder.encode("abc123"), Const.ROLE_ADMIN);
//			createUser("Client", "cliente", passwordEncoder.encode("def456"), Const.ROLE_CLIENT);
//		}
//	}
//	
//	public void createUser(String name,String email,String password,String roleName) {
//		
//		Role role =  new Role(roleName);
//		
//		this.roleRepository.save(role);
//		User user = new User(name, email, password, Arrays.asList(role));
//		userRepository.save(user);
//		
//	}
//
//}
