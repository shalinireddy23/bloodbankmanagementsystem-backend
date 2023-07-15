package com.cognizant.training.bloodBank.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.DonorRepository;
import com.cognizant.training.bloodBank.service.BloodBankService;

@Service
public class BloodBankServiceImpl implements BloodBankService {
	@Autowired
	DonorRepository donorRepository;
	
	
	@Autowired
	BloodBankRepository bloodBankRepository;
	
	 @Override
	public BloodBank save(BloodBank bb) {
		return bloodBankRepository.save(bb);
	}
	
	 @Override
	public void delete(BloodBank bb) {
		bloodBankRepository.delete(bb);
	}
	
	 @Override
	public int deleteByEmail(String email) {
		return bloodBankRepository.deleteByEmail(email);
	}
	 
	 @Override
	public List<BloodBank> findAll(){
		return bloodBankRepository.findAll();
	}
	
	 @Override
	public BloodBank findOneByEmail(String email) {

		return bloodBankRepository.findOneByEmail(email);
	}
	 
	 @Override
	public Optional<BloodBank> findOne(Long bbid) {
		
		return bloodBankRepository.findById(bbid);
	}
	 
	 @Override
	public List<BloodBank>  getBloodBankByLocation(String location){
		System.out.println(location);
		return bloodBankRepository.getBloodBankByLocation(location);
	}

	@Override
	public BloodBank login(BloodBank bb) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
