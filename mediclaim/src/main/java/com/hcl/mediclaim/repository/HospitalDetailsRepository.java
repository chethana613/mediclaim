package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.HospitalDetails;

@Repository
public interface HospitalDetailsRepository extends JpaRepository<HospitalDetails,Integer>{


		HospitalDetails findByhospitalId(int hospitalId);
}
