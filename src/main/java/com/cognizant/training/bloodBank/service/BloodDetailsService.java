package com.cognizant.training.bloodBank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.BloodDetails;
import com.cognizant.training.bloodBank.model.UpdateQuantityClass;

@Service
public interface BloodDetailsService {
	
	BloodDetails save(BloodDetails bd);
	BloodDetails getBloodDetailsById(Long bdId);
	List<BloodDetails> getBloodBankByLocation(String location);	
	List<BloodDetails> findAll();
	Optional<BloodDetails> findOne(Long bdid);
	void delete(BloodDetails bd);
	List<BloodDetails> getBloodBankByBloodGroup(String blood_group);
	void updateQuantity(UpdateQuantityClass updatequantityclass);

}
