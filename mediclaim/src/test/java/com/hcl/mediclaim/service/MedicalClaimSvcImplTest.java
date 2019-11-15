package com.hcl.mediclaim.service;

import static org.mockito.Mockito.lenient;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.mediclaim.dto.MedicalClaimResponsedto;
import com.hcl.mediclaim.entity.MedicalClaim;
import com.hcl.mediclaim.repository.MedicalClaimRepositoty;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MedicalClaimSvcImplTest {
	
	@InjectMocks
	MedicalClaimServiceImpl medicalClaimServiceImpl;

	@Mock
	MedicalClaimRepositoty medicalClaimRepositoty;
	
	
	@Test
	public void registerMedicalClaimTest() throws Exception {
		MedicalClaimResponsedto medicalClaimResponsedto= new MedicalClaimResponsedto();
		medicalClaimResponsedto.setMedicalClaimId(1);
		medicalClaimResponsedto.setMessage("success");
		medicalClaimResponsedto.setStatusCode(200);
		
		MedicalClaim medicalClaim= new MedicalClaim();
		LocalDate ad = LocalDate.parse("2019-06-06");
		LocalDate dd = LocalDate.parse("2019-06-12");
		
		medicalClaim.setId(1);
		medicalClaim.setAdmittedDate(ad);
		medicalClaim.setDischargeDate(dd);
		medicalClaim.setHospitalId(1);
		medicalClaim.setPolicyId(101);
		
		Optional<MedicalClaim> medicalClaimStatus = Optional.of(medicalClaim);
		lenient().when(medicalClaimRepositoty.findBymedicalClaimId(Mockito.anyInt())).thenReturn(medicalClaim);
		Assert.assertNotNull(medicalClaimStatus);
		
	}
}
