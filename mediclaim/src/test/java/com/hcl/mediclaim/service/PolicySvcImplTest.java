package com.hcl.mediclaim.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.mediclaim.dto.PolicyListResponsedto;
import com.hcl.mediclaim.entity.Policy;
import com.hcl.mediclaim.entity.UserPolicyClaim;
import com.hcl.mediclaim.exception.PolicyExpiredException;
import com.hcl.mediclaim.repository.PolicyRepository;
import com.hcl.mediclaim.repository.UserPolicyClaimRepository;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PolicySvcImplTest {
	
	@InjectMocks
	PolicyServiceImpl policySvcImpl;

	@Mock
	PolicyRepository policyRepository;
	@Mock
	public UserPolicyClaimRepository userPolicyClaimRepository;
	
	@Test(expected=PolicyExpiredException.class)
	public void policyValidityCheckNegative() throws Exception{
		
		Mockito.when(policyRepository.findBypolicyId(101)).thenReturn(null);
		Mockito.when(userPolicyClaimRepository.findBypolicyId(101)).thenReturn(null);	
		policySvcImpl.policyValidityCheck(101);
	}
	
	@Test
	public void policyValidityCheckDate() throws Exception{
		
		Policy policy = new Policy();
		policy.setPolicyId(101);
		UserPolicyClaim userPolicyClaim = new UserPolicyClaim();
		LocalDate dat= LocalDate.parse("2019-12-12");
		userPolicyClaim.setUserPolicyClaimId(1001);
		userPolicyClaim.setPolicyExpireDate(dat);
		Mockito.when(policyRepository.findBypolicyId(101)).thenReturn(policy);
        Mockito.when(userPolicyClaimRepository.findBypolicyId(101)).thenReturn(userPolicyClaim);	
        PolicyListResponsedto RPolicyResponse = policySvcImpl.policyValidityCheck(101);
       Assert.assertEquals("Valid Policy", RPolicyResponse.getMessage());
	}
	
	@Test(expected=PolicyExpiredException.class)
	public void policyValidityCheckDateNegative() throws Exception{
		
		Policy policy = new Policy();
		policy.setPolicyId(101);
		UserPolicyClaim userPolicyClaim = new UserPolicyClaim();
		LocalDate dat= LocalDate.parse("2019-10-10");
		userPolicyClaim.setUserPolicyClaimId(1001);
		userPolicyClaim.setPolicyExpireDate(dat);
		Mockito.when(policyRepository.findBypolicyId(101)).thenReturn(policy);
        Mockito.when(userPolicyClaimRepository.findBypolicyId(101)).thenReturn(userPolicyClaim);	
        policySvcImpl.policyValidityCheck(101);
       
	}

}
