package com.hcl.mediclaim.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.hcl.mediclaim.repository.UserPolicyClaimRepository;

@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {

	@Autowired
	public ClaimStatusRepository claimStatusRepository;

	@Autowired
	public MedicalClaimRepositoty medicalClaimRepositoty;

	@Autowired
	public UserPolicyClaimRepository userPolicyClaimRepository;

	@Autowired
	public PolicyServiceImpl policyServiceImpl;

	@Autowired
	public PolicyRepository policyRepository;

	@Autowired
	public HospitalDetailsRepository hospitalDetailsRepository;

	public ClaimStatusServiceResponsedto claimStatusService(int primaryApprover, int superiorApprover,
			int medicalClaimId){

		ClaimStatus claimStatus = new ClaimStatus();
		claimStatus.setApprover(primaryApprover);
		claimStatus.setSuperiorApprover(superiorApprover);
		claimStatus.setMedicalClaimId(medicalClaimId);
		claimStatus.setPrimaryApproverStatus(1);
		claimStatus.setSuperiorApproverStatus(0);
		claimStatus.setComments("Raised");
		claimStatus.setClaimStatusdesc(ClaimConstants.PENDING_STATUS);
		claimStatusRepository.save(claimStatus);

		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();
		claimStatusServiceResponsedto.setClaimStatusId(claimStatus.getClaimStatusId());
		claimStatusServiceResponsedto.setMessage("Claim Raised successfully");
		claimStatusServiceResponsedto.setStatusCode(200);

		return claimStatusServiceResponsedto;
	}

	public ClaimStatusServiceResponsedto checkClaimStatus(int claimStatusId) throws GeneralException {

		Optional<ClaimStatus> claimStatusUserInfo = claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus = null;
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();

		if (claimStatusUserInfo.isPresent()) {
			claimStatus = claimStatusUserInfo.get();

			int pas = claimStatus.getPrimaryApproverStatus();
			int sas = claimStatus.getSuperiorApproverStatus();
			String oas = claimStatus.getClaimStatusdesc();

			if (pas == 1) {
				claimStatusServiceResponsedto.setMessage(ClaimConstants.CLAIM_STATUS + oas + "," + "APPROVAL PENDING AT L1");
			} else if (pas == 3) {
				claimStatusServiceResponsedto.setMessage(ClaimConstants.CLAIM_STATUS + oas + "," + "APPROVAL REJECTED AT L1");
			} 
			else{
				if(sas == 0) {
					claimStatusServiceResponsedto.setMessage(ClaimConstants.CLAIM_STATUS + oas);
				}
				else if(sas == 1) {
					claimStatusServiceResponsedto.setMessage(ClaimConstants.CLAIM_STATUS + oas + "," + "APPROVAL PENDING AT L2");
				}
				else if(sas == 2) {
					claimStatusServiceResponsedto.setMessage(ClaimConstants.CLAIM_STATUS + oas);
				}
				else if(sas==3){
					claimStatusServiceResponsedto.setMessage("Claim Status:"+oas+","+"APPROVAL REJECTED AT L2");
				}
			}
			
			claimStatusServiceResponsedto.setClaimStatusId(claimStatusId);
			claimStatusServiceResponsedto.setStatusCode(200);
			claimStatusServiceResponsedto.setMediclaimID(claimStatus.getMedicalClaimId());
			return claimStatusServiceResponsedto;
		} else {
			throw new GeneralException("Failed to check claimstatus");
		}

	}

	public ClaimStatusServiceResponsedto approveClaim(int claimStatusId) throws GeneralException {

		Optional<ClaimStatus> claimStatusUserInfo = claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus = null;
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();

		if (claimStatusUserInfo.isPresent()) {
			claimStatus = claimStatusUserInfo.get();

			if (claimStatus.getClaimStatusdesc().equalsIgnoreCase(ClaimConstants.PENDING_STATUS)
					&& claimStatus.getPrimaryApproverStatus() == 1) {
				claimStatus.setPrimaryApproverStatus(2);
				claimStatus.setClaimStatusdesc("Approved");
				claimStatusRepository.save(claimStatus);
				claimStatusServiceResponsedto.setMessage("Claim Status Approved");
				return claimStatusServiceResponsedto;
				
			} else if (claimStatus.getClaimStatusdesc().equalsIgnoreCase(ClaimConstants.PENDING_STATUS)
					&& claimStatus.getPrimaryApproverStatus() == 2 && claimStatus.getSuperiorApproverStatus() == 1) {
				claimStatus.setSuperiorApproverStatus(2);
				claimStatus.setClaimStatusdesc("Approved");
				claimStatusRepository.save(claimStatus);
				claimStatusServiceResponsedto.setMessage("Claim Status Approved");
				return claimStatusServiceResponsedto;
				
			} else {
				claimStatusServiceResponsedto.setMessage("Claim Already" + claimStatus.getClaimStatusdesc());
				return claimStatusServiceResponsedto;
			}
		} else {
			throw new GeneralException("Failed to approve claim");
		}
	}

	public ClaimStatusServiceResponsedto rejectClaim(int claimStatusId, String comments) throws GeneralException{

		Optional<ClaimStatus> claimStatusUserInfo = claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();
		ClaimStatus claimStatus = null;
		if (claimStatusUserInfo.isPresent()) {
			claimStatus = claimStatusUserInfo.get();

			if (claimStatus.getClaimStatusdesc().equalsIgnoreCase(ClaimConstants.PENDING_STATUS)
					&& claimStatus.getPrimaryApproverStatus() == 1) {
				claimStatus.setPrimaryApproverStatus(3);
				claimStatus.setClaimStatusdesc("Rejected");
				claimStatusRepository.save(claimStatus);
				claimStatusServiceResponsedto.setMessage("Claim Status Rejected at L1");
				return claimStatusServiceResponsedto;
			} else if (claimStatus.getClaimStatusdesc().equalsIgnoreCase(ClaimConstants.PENDING_STATUS)
					&& claimStatus.getPrimaryApproverStatus() == 2 && claimStatus.getSuperiorApproverStatus() == 1) {
				claimStatus.setSuperiorApproverStatus(3);
				claimStatus.setClaimStatusdesc("Rejected");
				claimStatusRepository.save(claimStatus);
				claimStatusServiceResponsedto.setMessage("Claim Status Rejected at L2");
				return claimStatusServiceResponsedto;
			} else {
				claimStatusServiceResponsedto.setMessage("Claim Already" + claimStatus.getClaimStatusdesc());
				return claimStatusServiceResponsedto;
			}
		} else {
			throw new GeneralException("Failed to Reject Claim");
		}

	}

	public ClaimStatusServiceResponsedto assignClaimToSuperior(int claimStatusId) throws GeneralException{

		Optional<ClaimStatus> claimStatusUserInfo = claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();
		ClaimStatus claimStatus = null;
		if (claimStatusUserInfo.isPresent()) {
			claimStatus = claimStatusUserInfo.get();
		
		if (claimStatus.getClaimStatusdesc().equalsIgnoreCase(ClaimConstants.PENDING_STATUS) && claimStatus.getPrimaryApproverStatus() == 1
				&& claimStatus.getSuperiorApproverStatus() == 0) {
			claimStatus.setPrimaryApproverStatus(2);
			claimStatus.setSuperiorApproverStatus(1);
			claimStatusRepository.save(claimStatus);
			claimStatusServiceResponsedto.setMessage("Claim Assigned to L2");
			return claimStatusServiceResponsedto;
		} else {
			claimStatusServiceResponsedto.setMessage("Cannot Assign Claim to L2");
			return claimStatusServiceResponsedto;
		}
		}
		else {
			throw new GeneralException("Failed to assign claim to superior");
		}
	}

	public ClaimStatusServiceResponsedto viewClaim(int claimStatusId) throws GeneralException {
		Optional<ClaimStatus> claimStatusUserInfo = claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus = null;
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();
		if (claimStatusUserInfo.isPresent()) {
			claimStatus = claimStatusUserInfo.get();
		

		MedicalClaim medicalClaim = medicalClaimRepositoty.findBymedicalClaimId(claimStatus.getMedicalClaimId());
		Policy policy = policyRepository.findBypolicyId(medicalClaim.getPolicyId());
		HospitalDetails hospitalDetails = hospitalDetailsRepository.findByhospitalId(medicalClaim.getHospitalId());

		if (hospitalDetails.getPolicyId() == medicalClaim.getPolicyId()) {
			claimStatusServiceResponsedto.setPolicyAmount(policy.getPolicyAmount() * (85) / 100);
			
		}

		else {
			claimStatusServiceResponsedto.setPolicyAmount(policy.getPolicyAmount() * (80) / 100);
		}
		
			claimStatusServiceResponsedto.setClaimStatusId(claimStatus.getClaimStatusId());
			claimStatusServiceResponsedto.setHospitalName(hospitalDetails.getHospitalName());
			claimStatusServiceResponsedto.setPolicyName(policy.getPolicyName());
			claimStatusServiceResponsedto.setPolicyAmount(50L);
			claimStatusServiceResponsedto.setMessage("Success");
			claimStatusServiceResponsedto.setMediclaimID(claimStatus.getMedicalClaimId());
			claimStatusServiceResponsedto.setStatusCode(200);

		

		return claimStatusServiceResponsedto;
		}
		else {
			throw new GeneralException("Failed to view claim");
		}
		
	}

}
