package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.olx.entity.UserEntity;
import com.olx.repo.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService,UserCustomeDetailService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  List<UserEntity> userEntityList = userRepo.findByUsername(username);
	  if(userEntityList.size()==0) {
		  throw new UsernameNotFoundException(username);
	  }
	  UserEntity userEntity = userEntityList.get(0);
	  List<GrantedAuthority> authorities = new ArrayList<>();
	  authorities.add(new SimpleGrantedAuthority(userEntity.getRoles()));
	  User user = new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()),authorities);
	  return user;
	}

	@Override
	public com.olx.dto.User registerUser(com.olx.dto.User user) {
		
		UserEntity userEntity = getUserEntityFromDto(user);
		userEntity = userRepo.save(userEntity);	    
		return getUserDtoFromEntity(userEntity);	
	}
	
	private UserEntity getUserEntityFromDto(com.olx.dto.User user) { 
	   UserEntity userEntity = this.modelMapper.map(user,UserEntity.class);
	   return userEntity;
    }
	
	private com.olx.dto.User getUserDtoFromEntity(UserEntity userEntity) {		
	 com.olx.dto.User userDto = this.modelMapper.map(userEntity, com.olx.dto.User.class);
	 return userDto;
	 }
	
	private List<com.olx.dto.User> getUserDtoListFromEntityList(List<UserEntity> userEntityList){
		   List<com.olx.dto.User> userDtoList = new ArrayList<com.olx.dto.User>();
		   for(UserEntity userEntity: userEntityList) {
			   userDtoList.add(getUserDtoFromEntity(userEntity));
		   }
		   return userDtoList;
		}

	@Override
	public List<com.olx.dto.User> getAllUser() {
		
		List<UserEntity> advertisementList = userRepo.findAll();
		return getUserDtoListFromEntityList(advertisementList);
		
	}


}
