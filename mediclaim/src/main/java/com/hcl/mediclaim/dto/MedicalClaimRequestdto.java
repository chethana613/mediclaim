package com.hcl.mediclaim.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalClaimRequestdto implements Serializable {

	

	
	private static final long serialVersionUID = 1L;
	private LocalDate admittedDate;
	private LocalDate dischargeDate;
	private int policyId;
	private int hospitalId;
	private long claimAmount;
	private int id;
	
}
