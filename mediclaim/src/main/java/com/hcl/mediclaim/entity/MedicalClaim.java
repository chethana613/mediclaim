package com.hcl.mediclaim.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MedicalClaim")
public class MedicalClaim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer medicalClaimId;
	private LocalDate admittedDate;
	private LocalDate dischargeDate;
	private int policyId;
	private int hospitalId;
	private Long claimAmount;
	private int id;
	
	
	public Long getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Long claimAmount) {
		this.claimAmount = claimAmount;
	}
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	
	public LocalDate getAdmittedDate() {
		return admittedDate;
	}
	public void setAdmittedDate(LocalDate admittedDate) {
		this.admittedDate = admittedDate;
	}
	public LocalDate getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
