package com.cognizant.training.bloodBank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.training.bloodBank.model.BloodDetails;

public interface BloodDetailsRepository extends JpaRepository<BloodDetails, Long>{

	@Query(value = "SELECT * FROM blood_details WHERE location = :location", nativeQuery = true)
	List<BloodDetails> getBloodBankByLocation(String location);
	
//	Optional<BloodDetails> findByBloodGroup(String bloodGroup);


	//method created for fetching blood details data where bloodGroup = bloodGroup and bloodBankId = bloodBankId
	Optional<BloodDetails> findByBloodGroupAndBloodBankBloodBankId(String bloodGroup, Long bloodBankId);
	
	BloodDetails findByBloodBankBloodBankIdAndBloodGroup(Long bloodBankId, String bloodgroup);
	
	List<BloodDetails> findAllByBloodGroup(String bloodgroup);

}
