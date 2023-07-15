package com.cognizant.training.bloodBank.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.exception.BloodDetailsNotFoundException;
import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.model.BloodDetails;
import com.cognizant.training.bloodBank.model.UpdateQuantityClass;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.BloodDetailsRepository;
import com.cognizant.training.bloodBank.service.BloodDetailsService;

@Service
public class BloodDetailsServiceImpl implements BloodDetailsService{

	@Autowired
	BloodDetailsRepository bloodDetailsRepository;
	
	@Autowired
	BloodBankRepository bloodBankRepository;
	
	 @Override
	 public BloodDetails save(BloodDetails bd) {
		 if(!bd.getBloodGroup().equals("A+") && !bd.getBloodGroup().equals("A-") && !bd.getBloodGroup().equals("B+") && !bd.getBloodGroup().equals("B-") && !bd.getBloodGroup().equals("AB+") && !bd.getBloodGroup().equals("AB-") && !bd.getBloodGroup().equals("O+") && !bd.getBloodGroup().equals("O-")) {
				return null;
			}
	
		Optional<BloodDetails> bloodDetails = bloodDetailsRepository.findByBloodGroupAndBloodBankBloodBankId(bd.getBloodGroup(), bd.getBloodBank().getBloodBankId());
		if(!bloodDetails.isPresent()) {
			Optional<BloodBank> bloodBank = bloodBankRepository.findById(bd.getBloodBank().getBloodBankId());
			bd.setBloodBank(bloodBank.get());
			bd.setQuantity(1);
			return bloodDetailsRepository.save(bd);
		} 
		bloodDetails.get().setQuantity(bloodDetails.get().getQuantity() + bd.getQuantity());
		return bloodDetailsRepository.save(bloodDetails.get());
		
	}
	
	 @Override
	public void delete(BloodDetails bd) {
		bloodDetailsRepository.delete(bd);
	}
	
	 @Override
	public List<BloodDetails> findAll(){
		return bloodDetailsRepository.findAll();
	}
	
	 @Override
	public Optional<BloodDetails> findOne(Long bdid) {
		
		return bloodDetailsRepository.findById(bdid);
	}
	
//	 @Override
//	public Optional<BloodDetails> findById(long bloodid){
//		return bloodDetailsRepository.findById(bloodid);
//	}

	 public List<BloodDetails> getBloodBankByBloodGroup(String blood_group){
		 return bloodDetailsRepository.findAllByBloodGroup(blood_group);
	 }
	@Override
	public List<BloodDetails> getBloodBankByLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BloodDetails getBloodDetailsById(Long bdId) {
		
		return bloodDetailsRepository.findById(bdId)
				.orElseThrow(() -> new BloodDetailsNotFoundException("BloodDetails not found with ID" + bdId));
	}

	@Override
	public void updateQuantity(UpdateQuantityClass updatequantityclass) {
		
		BloodDetails bloodDetails = bloodDetailsRepository.findByBloodBankBloodBankIdAndBloodGroup(updatequantityclass.getBloodBankId(), updatequantityclass.getBloodGroup());
		if(bloodDetails == null) {
			return;
		}
		bloodDetails.setQuantity(bloodDetails.getQuantity() - updatequantityclass.getQuantity());
		bloodDetailsRepository.save(bloodDetails);
	}
}
