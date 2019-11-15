package com.hcl.mediclaim.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationdto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userMail;
	private String pass;
	private Long phone;

}
