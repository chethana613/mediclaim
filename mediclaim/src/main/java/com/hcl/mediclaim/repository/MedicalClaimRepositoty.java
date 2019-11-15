package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.MedicalClaim;

@Repository
public interface MedicalClaimRepositoty extends JpaRepository<MedicalClaim,Integer>{

	MedicalClaim findBymedicalClaimId(int medicalClaimId);
	
}
