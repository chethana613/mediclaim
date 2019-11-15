package com.hcl.mediclaim.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.constants.ClaimConstants;
import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;
import com.hcl.mediclaim.entity.ClaimUser;
import com.hcl.mediclaim.exception.UserException;
import com.hcl.mediclaim.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	public UserRepository userRepository;
	
	/**
	 *
	 */
	public LoginResponsedto login(LoginRequestdto loginRequestdto){
	
		Optional<ClaimUser> loginResponseto=userRepository.findByUserMailAndPass(loginRequestdto.getUserMail(),loginRequestdto.getPass());
		LoginResponsedto loginResponsedto= new LoginResponsedto();
		if(!(loginResponseto.isPresent())) {
			throw new UserException(ClaimConstants.INVALID_CREDENTIALS);
		}
		else {
			  loginResponsedto.setStatusCode(200);
			  loginResponsedto.setMessage("Login Successful"); 
			  return loginResponsedto; 
		}
	}

	
	

}
