package com.hcl.mediclaim.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;
import com.hcl.mediclaim.entity.ClaimUser;
import com.hcl.mediclaim.exception.UserException;
import com.hcl.mediclaim.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)

public class UserRegistrationSvcImplTest {

	
	@InjectMocks
	ClaimServiceRegisterImpl claimServiceRegisterImpl;
	
	@Mock
	UserRepository userRepository;

	
	UserRegistrationdto userRegistrationdto;
	UserRegistrationResponsedto userRegistrationResponsedto;
	ClaimUser claimUser;
	
	public UserRegistrationResponsedto getUserRegistrationResponseDto()
	{
		userRegistrationResponsedto = new UserRegistrationResponsedto();
		userRegistrationResponsedto.setMessage("User registered successfuly");
		userRegistrationResponsedto.setStatusCode(200);
		return userRegistrationResponsedto;
	}
	
	public UserRegistrationdto getUserRegistrationRequestDto()
	{
		userRegistrationdto = new UserRegistrationdto();
		userRegistrationdto.setUserMail("a@gmail.com");
		userRegistrationdto.setPass("ant");
		userRegistrationdto.setPhone(987L);
		return userRegistrationdto;
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
		claimUser= new ClaimUser();
		userRegistrationResponsedto = getUserRegistrationResponseDto();
		 userRegistrationdto = getUserRegistrationRequestDto();
	}
	
	@Test
	public void testRegisterUser()
	{
		claimUser= new ClaimUser();
		Optional<ClaimUser> optinalUser = Optional.of(claimUser);	
		lenient().when(userRepository.findByuserMail("hello@gmail.com")).thenReturn(optinalUser);
		UserRegistrationResponsedto response = claimServiceRegisterImpl.register(userRegistrationdto);
		assertEquals("Created", response.getMessage());
	}
	
	@Test(expected=UserException.class)
	public void testRegisterUserNegative()
	{
		claimUser= new ClaimUser();
		Optional<ClaimUser> optinalUser = Optional.of(claimUser);
		lenient().when(userRepository.findByuserMail("a@gmail.com")).thenReturn(optinalUser);
		UserRegistrationResponsedto response = claimServiceRegisterImpl.register(userRegistrationdto);
		Assert.assertNotNull(response);
	}
	
}
