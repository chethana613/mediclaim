package com.hcl.mediclaim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="claimStatus")
public class ClaimStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claimStatusId;
	private String claimStatusdesc;
	private int approver;
	private int superiorApprover;
	private int primaryApproverStatus;
	private int superiorApproverStatus;
	private int medicalClaimId;
	private String comments;
	
}
