package com.cognizant.training.bloodBank.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.model.BloodDetails;
import com.cognizant.training.bloodBank.model.Donor;
import com.cognizant.training.bloodBank.model.Login;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.BloodDetailsRepository;
import com.cognizant.training.bloodBank.repository.DonorRepository;
import com.cognizant.training.bloodBank.service.DonorService;

@Service
public class DonorServiceImpl implements DonorService{
	
	@Autowired
	BloodBankRepository bloodBankRepository;

	@Autowired
	DonorRepository donorRepository;
	@Autowired
	BloodDetailsRepository blooddetailsRepository;
	
	@Override
	public Donor save(Donor donor) {
		Optional<BloodBank> bloodBank = bloodBankRepository.findById(donor.getBloodBank().getBloodBankId());
		donor.setBloodBank(bloodBank.get());
		
		Optional<BloodDetails> blooddetails= blooddetailsRepository.findByBloodGroupAndBloodBankBloodBankId(donor.getBlood_group(),bloodBank.get().getBloodBankId());
		if(blooddetails.isPresent()) {
		blooddetails.get().setQuantity(blooddetails.get().getQuantity()+donor.getQuantity());
		blooddetailsRepository.save(blooddetails.get());
		return donorRepository.save(donor);
		}
		BloodDetails newBloodDetails = new BloodDetails(donor.getBlood_group(), donor.getQuantity(), bloodBank.get());
		blooddetailsRepository.save(newBloodDetails);
		return donorRepository.save(donor);
	
	}
	
	@Override
	public void delete(Donor donor) {
		donorRepository.delete(donor);
	}
	
	@Override
	public Donor findOneByEmail(String email) {
		
		return donorRepository.findOneByEmail(email);
	}
	
	@Override
	public List<Donor> findAll(){
		return donorRepository.findAll();
	}
	

	public Optional<Donor> findOne(Long did) {
		
		return donorRepository.findById(did);
	}

	@Override
	public Donor login(Login login) {
		String tempEmail=login.getEmail();
		String tempPass= login.getPassword();
		if(tempEmail==null||tempPass==null)
			return null;
		Donor obj=donorRepository.findOneByEmail(tempEmail);
		if(obj!=null)
			return obj;
		else return null;
		
	}

	@Override
	public List<Donor>  getDonorByLocation(String location){
		return donorRepository.getDonorByLocation(location);
	}
}
