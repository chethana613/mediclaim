package com.hcl.mediclaim.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimStatusServiceResponsedto implements Serializable{
	private static final long serialVersionUID=1L;
	

	private Integer claimStatusId;
	private String message;
	private Integer statusCode;
	private Integer mediclaimID;
	private String hospitalName;
	private String policyName;
	private Long policyAmount;
	
}
