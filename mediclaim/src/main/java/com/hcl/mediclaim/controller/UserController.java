package com.hcl.mediclaim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;
import com.hcl.mediclaim.service.ClaimServiceRegisterImpl;
import com.hcl.mediclaim.service.LoginService;

@RestController
@RequestMapping("/claimUser")
public class UserController {
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ClaimServiceRegisterImpl claimServiceRegister;
		
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponsedto> register(@RequestBody UserRegistrationdto  claimRequestdto){
		logger.info("Inside Registation Controller");
		UserRegistrationResponsedto response=claimServiceRegister.register(claimRequestdto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);		
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponsedto> login(@RequestBody LoginRequestdto  loginRequestdto){
		logger.info("Inside Login Controller");
		LoginResponsedto response=loginService.login(loginRequestdto);
		return  new ResponseEntity<>(response, HttpStatus.OK);		
	}
}
