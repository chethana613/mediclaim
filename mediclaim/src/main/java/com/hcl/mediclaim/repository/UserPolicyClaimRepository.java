package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.UserPolicyClaim;

@Repository
public interface UserPolicyClaimRepository extends JpaRepository<UserPolicyClaim,Integer> {

	UserPolicyClaim findBypolicyId(int policyId);
}
