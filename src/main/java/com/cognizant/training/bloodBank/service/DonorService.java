package com.cognizant.training.bloodBank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.Donor;
import com.cognizant.training.bloodBank.model.Login;

@Service
public interface DonorService {
	
	Donor save(Donor donor);
	
	void delete(Donor donor);
	
	Donor findOneByEmail(String email);
	
	List<Donor> findAll();

	Optional<Donor> findOne(Long did);

	Donor login(Login login);
	
	List<Donor>  getDonorByLocation(String location);

}
