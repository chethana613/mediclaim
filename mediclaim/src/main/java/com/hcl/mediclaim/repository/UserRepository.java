package com.hcl.mediclaim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.ClaimUser;

@Repository
public interface UserRepository extends JpaRepository<ClaimUser,Integer> {
	Optional<ClaimUser> findByuserMail(String userMail);
	Optional<ClaimUser> findByUserMailAndPass(String userMail, String password);	
}
