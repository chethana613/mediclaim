package com.hcl.mediclaim.service;

import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.MedicalClaimRequestdto;

@Service
public interface MedicalClaimService {

	public String registerMedicalClaim(MedicalClaimRequestdto medicalClaimRequestdto);
}
