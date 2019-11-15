package com.hcl.mediclaim.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;
import com.hcl.mediclaim.service.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)

@WebAppConfiguration
public class LoginControllerTest {
	private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	@InjectMocks
	UserController userController;

	@Mock
	LoginServiceImpl loginServiceImpl;
	
	
	LoginRequestdto loginRequestdto;
	LoginResponsedto loginResponsedto;
	
	public LoginRequestdto getUserLoginRequestdto() {
		loginRequestdto = new LoginRequestdto();
		loginRequestdto.setUserMail("a@gmail.com");
		loginRequestdto.setPass("antey");
		return loginRequestdto;
	}

	public LoginResponsedto getUserLoginResponsedto() {
		loginResponsedto = new LoginResponsedto();
		loginResponsedto.setMessage("User Logged in Successfully");
		return loginResponsedto;
	}
	
	@Before
	public void setUp() {
		
		loginRequestdto = getUserLoginRequestdto();
		loginResponsedto = getUserLoginResponsedto();	
	}

	@Test
	public void userLoginTest() throws Exception{
		logger.info("Entering into userLoginTest");
		Mockito.when(loginServiceImpl.login(Mockito.any())).thenReturn(loginResponsedto);
		ResponseEntity<LoginResponsedto> response = userController.login(loginRequestdto);
		Assert.assertNotNull(response);
								
	}

}
