package com.hcl.mediclaim.service;

import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.PolicyListResponsedto;

@Service
public interface PolicyService {
	
	
	public PolicyListResponsedto policyValidityCheck(int policyID) ;
}
