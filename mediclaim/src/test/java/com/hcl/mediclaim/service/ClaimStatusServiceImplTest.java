package com.hcl.mediclaim.service;

import static org.mockito.Mockito.lenient;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.mediclaim.constants.ClaimConstants;
import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.entity.ClaimStatus;
import com.hcl.mediclaim.entity.HospitalDetails;
import com.hcl.mediclaim.entity.MedicalClaim;
import com.hcl.mediclaim.entity.Policy;
import com.hcl.mediclaim.exception.GeneralException;
import com.hcl.mediclaim.repository.ClaimStatusRepository;
import com.hcl.mediclaim.repository.HospitalDetailsRepository;
import com.hcl.mediclaim.repository.MedicalClaimRepositoty;
import com.hcl.mediclaim.repository.PolicyRepository;




@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClaimStatusServiceImplTest {

	@InjectMocks
	ClaimStatusServiceImpl claimStatusServiceImpl;
	
	@InjectMocks
	PolicyServiceImpl policySvcImpl;

	@Mock
	ClaimStatusRepository claimStatusRepository;
	
	@Mock
	MedicalClaimRepositoty medicalClaimRepositoty;
	
	@Mock
	PolicyRepository policyRepository;
	
	@Mock
	HospitalDetailsRepository hospitalDetailsRepository;
	
	ClaimStatus claimStatus = null;
	
	@Before
	public void setUp() {
		claimStatus = new ClaimStatus();
		claimStatus.setClaimStatusdesc(ClaimConstants.PENDING_STATUS);
	}

	
	@Test
	public void checkClaimStatusTest() throws Exception {
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();
		claimStatusServiceResponsedto.setClaimStatusId(1);
		claimStatusServiceResponsedto.setMessage("checkClaimStatusTest success");
		claimStatusServiceResponsedto.setStatusCode(200);
		ClaimStatus claimStatus1 = new ClaimStatus();
		claimStatus1.setPrimaryApproverStatus(1);
		claimStatus1.setSuperiorApproverStatus(2);
		claimStatus1.setClaimStatusId(2);
		claimStatus1.setClaimStatusdesc(ClaimConstants.PENDING_STATUS);

		Optional<ClaimStatus> optinalClaimStatus = Optional.of(claimStatus1);
		lenient().when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(optinalClaimStatus);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.claimStatusService(1, 3, 1);
		Assert.assertNotNull(response);
	}
	 
	
	@Test
	public void checkClaimStatusTestAsPas1() throws Exception {
		claimStatus.setPrimaryApproverStatus(1);
		Optional<ClaimStatus> optional = Optional.of(claimStatus);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optional);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.checkClaimStatus(2);
		Assert.assertNotNull(response);
	}
	
	@Test(expected=GeneralException.class)
	public void checkClaimStatusException() throws Exception {
		Mockito.when(claimStatusRepository.findByclaimStatusId(22)).thenReturn(Optional.ofNullable(null));
		claimStatusServiceImpl.checkClaimStatus(22);
	}
	
	@Test
	public void checkClaimStatusTestAsPas3() throws Exception {
		claimStatus.setPrimaryApproverStatus(3);
		Optional<ClaimStatus> optional = Optional.of(claimStatus);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optional);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.checkClaimStatus(2);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void checkClaimStatusTestAsPas2Sas0() throws Exception {
		claimStatus.setSuperiorApproverStatus(0);
		Optional<ClaimStatus> optional = Optional.of(claimStatus);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optional);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.checkClaimStatus(2);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void checkClaimStatusTestAsPas2Sas2() throws Exception {
		claimStatus.setSuperiorApproverStatus(2);
		Optional<ClaimStatus> optional = Optional.of(claimStatus);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optional);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.checkClaimStatus(2);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void checkClaimStatusTestAsPas2Sas1() throws Exception {
		claimStatus.setSuperiorApproverStatus(1);
		Optional<ClaimStatus> optional = Optional.of(claimStatus);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optional);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.checkClaimStatus(2);
		Assert.assertNotNull(response);
	}
	@Test
	public void checkClaimStatusTestAsPas2Sas3() throws Exception {
		claimStatus.setSuperiorApproverStatus(3);
		Optional<ClaimStatus> optional = Optional.of(claimStatus);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optional);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.checkClaimStatus(2);
		Assert.assertNotNull(response);
	}
		
	@Test(expected=GeneralException.class)
	public void approveClaimTestException() throws Exception {
		Mockito.when(claimStatusRepository.findByclaimStatusId(22)).thenReturn(Optional.ofNullable(null));
		claimStatusServiceImpl.approveClaim(22);		
	}
	
	@Test
	public void approveClaimTest() throws Exception {
		claimStatus.setPrimaryApproverStatus(1);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.approveClaim(2);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void approveClaimTestPas2() throws Exception {
		claimStatus.setPrimaryApproverStatus(2);
		claimStatus.setSuperiorApproverStatus(1);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.approveClaim(2);
		Assert.assertNotNull(response);
	}
	@Test
	public void approveClaimTestPas3() throws Exception {
		claimStatus.setPrimaryApproverStatus(3);
		Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.approveClaim(2);
		Assert.assertNotNull(response);
	}
	
	
	@Test(expected=GeneralException.class)
	public void rejectClaimTestException() throws Exception {
		Mockito.when(claimStatusRepository.findByclaimStatusId(22)).thenReturn(Optional.ofNullable(null));
		claimStatusServiceImpl.rejectClaim(22, "NA");		
	}
	
	@Test
	public void rejectClaimTest() throws Exception {
		claimStatus.setPrimaryApproverStatus(1);
		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.rejectClaim(1, "NA");
		Assert.assertNotNull(response);
	}
	
	@Test
	public void rejectClaimTestPas2() throws Exception {
		claimStatus.setPrimaryApproverStatus(2);
		claimStatus.setSuperiorApproverStatus(1);
		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.rejectClaim(1, "NA");
		Assert.assertNotNull(response);
	}
	@Test
	public void rejectClaimTestPas3() throws Exception {
		claimStatus.setPrimaryApproverStatus(3);
		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.rejectClaim(1, "NA");
		Assert.assertNotNull(response);
	}
	
	
	@Test(expected=GeneralException.class)
	public void assignClaimTestException() throws Exception {
		Mockito.when(claimStatusRepository.findByclaimStatusId(19)).thenReturn(Optional.ofNullable(null));
		claimStatusServiceImpl.assignClaimToSuperior(19);			
	}
	
	@Test
	public void assignClaimTest() throws Exception {
		claimStatus.setPrimaryApproverStatus(1);
		claimStatus.setSuperiorApproverStatus(0);
		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.assignClaimToSuperior(1);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void assignClaimTestPas2() throws Exception {
		claimStatus.setPrimaryApproverStatus(2);
		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.assignClaimToSuperior(1);
		Assert.assertNotNull(response);
	}
	
	
	@Test(expected = GeneralException.class)
	public void viewClaimTestException() throws Exception {	
		Mockito.when(claimStatusRepository.findByclaimStatusId(19)).thenReturn(Optional.ofNullable(null));
		claimStatusServiceImpl.viewClaim(19);
	}
	
	
	
	@Test
	public void viewClaimWaiverTest() throws Exception {

		claimStatus.setClaimStatusId(1);
		claimStatus.setMedicalClaimId(1);
		MedicalClaim medicalClaim = new MedicalClaim();
		medicalClaim.setId(1);
		medicalClaim.setPolicyId(101);
		medicalClaim.setHospitalId(1);
		HospitalDetails hospitalDetails = new HospitalDetails();
		hospitalDetails.setHospitalId(1);
		hospitalDetails.setPolicyId(101);
		Policy policy = new Policy();
		policy.setPolicyId(101);
		policy.setPolicyAmount(76576L);

		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		Mockito.when(medicalClaimRepositoty.findBymedicalClaimId(1)).thenReturn(medicalClaim);
		Mockito.when(policyRepository.findBypolicyId(101)).thenReturn(policy);
		Mockito.when(hospitalDetailsRepository.findByhospitalId(1)).thenReturn(hospitalDetails);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.viewClaim(1);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void viewClaimNoWaiver() throws Exception {

		claimStatus.setClaimStatusId(1);
		claimStatus.setMedicalClaimId(1);
		MedicalClaim medicalClaim = new MedicalClaim();
		medicalClaim.setId(1);
		medicalClaim.setPolicyId(101);
		medicalClaim.setHospitalId(1);
		HospitalDetails hospitalDetails = new HospitalDetails();
		hospitalDetails.setHospitalId(1);
		hospitalDetails.setPolicyId(109);
		Policy policy = new Policy();
		policy.setPolicyId(101);
		policy.setPolicyAmount(76576L);

		Mockito.when(claimStatusRepository.findByclaimStatusId(1)).thenReturn(Optional.ofNullable(claimStatus));
		Mockito.when(medicalClaimRepositoty.findBymedicalClaimId(1)).thenReturn(medicalClaim);
		Mockito.when(policyRepository.findBypolicyId(101)).thenReturn(policy);
		Mockito.when(hospitalDetailsRepository.findByhospitalId(1)).thenReturn(hospitalDetails);
		ClaimStatusServiceResponsedto response = claimStatusServiceImpl.viewClaim(1);
		Assert.assertNotNull(response);
	}
	 
}
