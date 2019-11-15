package com.hcl.mediclaim.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;



@Service
public interface ClaimServiceRegister {

	public UserRegistrationResponsedto register(@RequestBody UserRegistrationdto  claimRequestdto);

	
}
