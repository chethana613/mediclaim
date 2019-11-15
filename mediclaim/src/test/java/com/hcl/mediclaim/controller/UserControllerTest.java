
package com.hcl.mediclaim.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;
import com.hcl.mediclaim.service.ClaimServiceRegisterImpl;


@RunWith(MockitoJUnitRunner.class)

@WebAppConfiguration
public class UserControllerTest {

	private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	@InjectMocks
	UserController userController;

	@Mock
	ClaimServiceRegisterImpl claimServiceRegisterImpl;

	MockMvc mockMvc;

	UserRegistrationdto userRegistrationdto;
	UserRegistrationResponsedto userRegistrationResponsedto;

	public UserRegistrationdto getUserRegistrationdto() {
		userRegistrationdto = new UserRegistrationdto();
		userRegistrationdto.setUserMail("happy@gmail.com");
		userRegistrationdto.setPass("happy");
		userRegistrationdto.setPhone(987L);
		return userRegistrationdto;
	}

	public UserRegistrationResponsedto getUserRegistrationResponsedto() {
		userRegistrationResponsedto = new UserRegistrationResponsedto();
		userRegistrationResponsedto.setMessage("User Registered Successfully");
		return userRegistrationResponsedto;
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		userRegistrationdto = getUserRegistrationdto();
		userRegistrationResponsedto = getUserRegistrationResponsedto();
	}

	@Test
	public void registerUserTest() throws Exception {
		logger.info("Inside registerUserTest method of UserControllerTest");
		mockMvc.perform(MockMvcRequestBuilders.post("/claimUser/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(userRegistrationdto)))
				.andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
