package com.hcl.mediclaim.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.constants.ExceptionConstants;
import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;
import com.hcl.mediclaim.entity.ClaimUser;
import com.hcl.mediclaim.exception.UserException;
import com.hcl.mediclaim.repository.UserRepository;
@Service
public class ClaimServiceRegisterImpl implements ClaimServiceRegister{
	
	@Autowired
	public UserRepository userRepository;
	
	

	public UserRegistrationResponsedto register(UserRegistrationdto claimRequestdto) {
		
		Optional<ClaimUser> checkRegisterForEmail=userRepository.findByuserMail(claimRequestdto.getUserMail());
		if(checkRegisterForEmail.isPresent()) {
			throw new UserException(ExceptionConstants.EMAIL_EXISTS);
		}
		ClaimUser claimUser= new ClaimUser();
		
		claimUser.setUserMail(claimRequestdto.getUserMail());
		claimUser.setPass(claimRequestdto.getPass());
		claimUser.setPhone(claimRequestdto.getPhone());		
		userRepository.save(claimUser);		
		
		
		UserRegistrationResponsedto userRegistrationResponsedto= new UserRegistrationResponsedto();	
		userRegistrationResponsedto.setStatusCode(200);
		userRegistrationResponsedto.setMessage("Created");
		return userRegistrationResponsedto;		
	}
}



	