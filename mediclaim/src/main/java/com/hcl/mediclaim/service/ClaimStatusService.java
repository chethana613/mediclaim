package com.hcl.mediclaim.service;

import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.exception.GeneralException;

@Service
public interface ClaimStatusService {

	public ClaimStatusServiceResponsedto claimStatusService(int primaryApprover,int superiorApprover,int medicalClaimId);
	
	public ClaimStatusServiceResponsedto checkClaimStatus(int claimStatusId) throws GeneralException;
	
	public ClaimStatusServiceResponsedto approveClaim(int claimStatusId) throws GeneralException;
	
	public ClaimStatusServiceResponsedto rejectClaim(int claimStatusId,String comments) throws GeneralException;
	
	public ClaimStatusServiceResponsedto assignClaimToSuperior(int claimStatusId) throws GeneralException;
	
	public ClaimStatusServiceResponsedto viewClaim(int claimStatusId) throws GeneralException;
	
	
}
