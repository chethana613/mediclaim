package com.hcl.mediclaim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.dto.MedicalClaimRequestdto;
import com.hcl.mediclaim.exception.GeneralException;
import com.hcl.mediclaim.service.ClaimStatusService;
import com.hcl.mediclaim.service.MedicalClaimService;

@RestController
@RequestMapping("/claim")

public class ClaimController {
	
	@Autowired
	private MedicalClaimService medicalClaimService;
	
	@Autowired
	private ClaimStatusService claimStatusService;
	

	@PostMapping("/medicalClaim")
	public String registerMedicalClaim(@RequestBody MedicalClaimRequestdto medicalClaimRequestdto){	
		return medicalClaimService.registerMedicalClaim(medicalClaimRequestdto);
	}
	
	@PostMapping(value = "/raiseClaim/{primaryApprover}/{superiorApprover}/{medicalClaimId}")
	public ResponseEntity<ClaimStatusServiceResponsedto> raiseClaim(@PathVariable(value="primaryApprover") int primaryApprover,@PathVariable(value="superiorApprover") int superiorApprover,@PathVariable(value="medicalClaimId") int medicalClaimId){	
		ClaimStatusServiceResponsedto response=claimStatusService.claimStatusService(primaryApprover,superiorApprover,medicalClaimId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);	
	}
	
	@GetMapping(value="/checkClaimStatus/{claimStatusID}")
	public ResponseEntity<ClaimStatusServiceResponsedto> checkClaimStatus(@PathVariable(value="claimStatusID") int claimStatusID) throws GeneralException{
		ClaimStatusServiceResponsedto response= claimStatusService.checkClaimStatus(claimStatusID);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/approve/{claimStatusID}")
	public ResponseEntity<ClaimStatusServiceResponsedto> approveClaim(@PathVariable(value="claimStatusID") int claimStatusID) throws GeneralException{
		ClaimStatusServiceResponsedto response= claimStatusService.approveClaim(claimStatusID);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value="/rejectClaim/{claimStatusID}/{comments}")
	public ResponseEntity<ClaimStatusServiceResponsedto> rejectClaim(@PathVariable(value="claimStatusID") int claimStatusID, @PathVariable(value="comments") String comments) throws GeneralException{
		ClaimStatusServiceResponsedto response= claimStatusService.rejectClaim(claimStatusID, comments);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/assignClaimToSuperior/{claimStatusID}")
	public ResponseEntity<ClaimStatusServiceResponsedto> assignClaimToSuperior(@PathVariable(value="claimStatusID") int claimStatusID) throws GeneralException{
		ClaimStatusServiceResponsedto response= claimStatusService.assignClaimToSuperior(claimStatusID);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/viewClaim/{claimStatusID}")
	public ResponseEntity<ClaimStatusServiceResponsedto> viewClaim(@PathVariable(value="claimStatusID") int claimStatusID) throws GeneralException{
		ClaimStatusServiceResponsedto response=claimStatusService.viewClaim(claimStatusID);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
}
