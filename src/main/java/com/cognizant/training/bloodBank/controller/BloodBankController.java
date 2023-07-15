package com.cognizant.training.bloodBank.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.training.bloodBank.exception.BloodBankNotFoundException;
import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.service.BloodBankService;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class BloodBankController {
	
	@Autowired
	BloodBankService bloodBankService;

	
	@PostMapping("/blood-bank")
	public BloodBank createBloodBank(@Valid @RequestBody BloodBank bb) {
		
		return bloodBankService.save(bb);
	}

	@PostMapping("/blood-bank/login")
	public BloodBank login(@Valid @RequestBody BloodBank bb) {
		return bloodBankService.login(bb);
	}
	
	
	@GetMapping("/getbloodbank")
	public List<BloodBank> getAllBloodBanks() {
		System.out.println("getAll");
		return bloodBankService.findAll();
	}
	
	@GetMapping("/blood-bank/{location}")
	public List<BloodBank> getBloodBankByLocation(@PathVariable(value = "location") String location){
		return bloodBankService.getBloodBankByLocation(location);
	}
	
	
	@DeleteMapping("/blood-bank/{email}")
    public ResponseEntity<BloodBank> deleteBloodBank(@PathVariable(value = "email") String email) {
        BloodBank bloodBank = bloodBankService.findOneByEmail(email);
        if (bloodBank == null) {
            throw new BloodBankNotFoundException("BloodBank not found with email: " + email);
        }

        bloodBankService.deleteByEmail(email);

        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/blood-bank/")
    public ResponseEntity<BloodBank> updateBloodBank(@Valid @RequestBody BloodBank bb) {
        BloodBank bbOld = bloodBankService.findOneByEmail(bb.getEmail());
        if (bbOld == null) {
            throw new BloodBankNotFoundException("BloodBank not found with email: " + bb.getEmail());
        }
		bb.setBloodBankId(bbOld.getBloodBankId());
		bloodBankService.save(bb);
		 return ResponseEntity.ok().build();

	}
	
	
}
