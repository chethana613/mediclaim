package com.hcl.mediclaim.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserPolicyClaim")
public class UserPolicyClaim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userPolicyClaimId;
	private int id;
	private int policyId;
	private LocalDate policyExpireDate;
	private long maximumClaimAmount;
	
	public long getMaximumClaimAmount() {
		return maximumClaimAmount;
	}
	public void setMaximumClaimAmount(long maximumClaimAmount) {
		this.maximumClaimAmount = maximumClaimAmount;
	}
	public int getUserPolicyClaimId() {
		return userPolicyClaimId;
	}
	public void setUserPolicyClaimId(int userPolicyClaimId) {
		this.userPolicyClaimId = userPolicyClaimId;
	}
	
	public int getUserId() {
		return id;
	}
	public void setUserId(int userId) {
		this.id = userId;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
	public LocalDate getPolicyExpireDate() {
		return policyExpireDate;
	}
	public void setPolicyExpireDate(LocalDate policyExpireDate) {
		this.policyExpireDate = policyExpireDate;
	}
	
		
}
