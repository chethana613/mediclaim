package com.hcl.mediclaim.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;
import com.hcl.mediclaim.entity.ClaimUser;
import com.hcl.mediclaim.exception.UserException;
import com.hcl.mediclaim.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class LoginSvcImplTest {
	
	@InjectMocks
	LoginServiceImpl loginServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	
	
	LoginRequestdto loginRequestdto;
	LoginResponsedto loginResponsedto;
	ClaimUser claimUser;
	public LoginResponsedto getUserLoginResponseDto()
	{
		loginResponsedto = new LoginResponsedto();
		loginResponsedto.setMessage("User Login Success");
		loginResponsedto.setStatusCode(200);
		return loginResponsedto;
	}
	
	public LoginRequestdto getUserLoginRequestDto()
	{
		loginRequestdto = new LoginRequestdto();
		loginRequestdto.setUserMail("a@gmail.com");
		loginRequestdto.setPass("ant");
		return loginRequestdto;
	}
	public ClaimUser getRegistration() {
		 claimUser= new ClaimUser();
		 claimUser.setUserMail("a@gmail.com");
		 claimUser.setPass("ant");
		 claimUser.setPhone(987L);		 
		 return claimUser;
		
	}
	@Before
	public void setup()
	{
	
		loginResponsedto = getUserLoginResponseDto();
		loginRequestdto = getUserLoginRequestDto();
	}
	
	@Test
	public void loginTest() throws Exception{
		claimUser= new ClaimUser();
		loginRequestdto.setUserMail("a@gmail.com");
		loginRequestdto.setPass("ant");
		Optional<ClaimUser> optinalUser = Optional.of(claimUser);
		Mockito.when(userRepository.findByUserMailAndPass("a@gmail.com", "ant")).thenReturn(optinalUser);
		LoginResponsedto response=loginServiceImpl.login(loginRequestdto);
		Assert.assertNotNull(response);
		
		
	}
	@Test(expected=UserException.class)
	public void loginTestNegative() throws Exception{
		loginRequestdto.setUserMail("a@gmail.com");
		loginRequestdto.setPass("sat");
		Mockito.when(userRepository.findByUserMailAndPass("a@gmail.com", "sat")).thenReturn(Optional.ofNullable(null));
		loginServiceImpl.login(loginRequestdto);
	}
	
		

}
