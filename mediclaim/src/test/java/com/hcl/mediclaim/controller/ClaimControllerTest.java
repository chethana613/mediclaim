
package com.hcl.mediclaim.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.service.ClaimStatusService;
import com.hcl.mediclaim.service.ClaimStatusServiceImpl;



@RunWith(MockitoJUnitRunner.class)

public class ClaimControllerTest {
	
	@Mock
	ClaimStatusService claimStatusService;
	
	@Mock
	ClaimStatusServiceImpl claimStatusServiceImpl;
	
	@InjectMocks
	ClaimController ClaimController;
	
	ClaimStatusServiceResponsedto claimStatusServiceResponsedto= null;
	
	@Before
	public void setUp() {
		claimStatusServiceResponsedto= new ClaimStatusServiceResponsedto();
		claimStatusServiceResponsedto.setClaimStatusId(1);
		claimStatusServiceResponsedto.setMessage("success");
		claimStatusServiceResponsedto.setStatusCode(200);
	}

	@Test
	public void raiseClaimTest() throws Exception{	
		Mockito.when(claimStatusService.claimStatusService(1, 2, 3)).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> claimResponse = ClaimController.raiseClaim(1, 2, 3);
		assertNotNull(claimResponse);
	}
	
	@Test
	public void checkClaimTest() throws Exception{
		Mockito.when(claimStatusService.checkClaimStatus(Mockito.anyInt())).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> claimResponse = ClaimController.checkClaimStatus(1);
		Assert.assertEquals("success", claimResponse.getBody().getMessage());
	}
	
	@Test
	public void approveClaimTest() throws Exception {
		Mockito.when(claimStatusService.approveClaim(Mockito.anyInt())).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> response = ClaimController.approveClaim(1);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void rejectClaimTest() throws Exception {
		claimStatusServiceResponsedto.setClaimStatusId(2);
		Mockito.when(claimStatusService.rejectClaim(2, "NA")).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> response = ClaimController.rejectClaim(2, "NA");
		Assert.assertNotNull(response);
	}
	
	@Test
	public void assignClaimTest() throws Exception {
		claimStatusServiceResponsedto.setClaimStatusId(2);
		Mockito.when(claimStatusService.assignClaimToSuperior(2)).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> response = ClaimController.assignClaimToSuperior(2);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void viewClaimTest() throws Exception {
		claimStatusServiceResponsedto.setClaimStatusId(2);
		Mockito.when(claimStatusService.viewClaim(2)).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> response = ClaimController.viewClaim(2);
		Assert.assertNotNull(response);
	}
	
}
