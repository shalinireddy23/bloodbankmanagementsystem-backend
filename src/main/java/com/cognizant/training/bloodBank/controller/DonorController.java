package com.cognizant.training.bloodBank.controller;

import java.util.List;
import java.util.Optional;

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
import com.cognizant.training.bloodBank.exception.DonorNotFoundException;
import com.cognizant.training.bloodBank.model.Donor;
import com.cognizant.training.bloodBank.model.Login;
import com.cognizant.training.bloodBank.service.DonorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class DonorController {

	@Autowired
	DonorService donorService;
	 static Long curr_donor; 

	@PostMapping("/donor")
	public Donor createDonor(@Valid @RequestBody Donor donor) {
		return donorService.save(donor);
	}
	
	
	
	@PostMapping("/donor/login")
	public Donor LoginDonor(@RequestBody Login login) {
		Donor obj= donorService.login(login);
		curr_donor=obj.getDonor_id();
		return obj;
	}

	@PostMapping("/donor/logout")
	public Long LogoutDonor() {
		curr_donor=0L;
		return curr_donor;
	}


	@GetMapping("/getdonor")
	public List<Donor> getAllDonors() {
		System.out.println("getAll");
		return donorService.findAll();
	}

	@GetMapping("/donor/id/{id}")
	public ResponseEntity<Donor> getDonorById(@PathVariable(value = "id") Long dId) {
		Optional<Donor> donor = donorService.findOne(dId);
		if (!donor.isPresent()) {
			throw new DonorNotFoundException("Donor not found with ID: " + dId);
		}

		return ResponseEntity.ok(donor.get());
	}

	@DeleteMapping("/donor/{id}")
	public ResponseEntity<Donor> deleteDonor(@PathVariable(value = "id") Long dId) {
		Optional<Donor> donor = donorService.findOne(dId);
		if (!donor.isPresent()) {
			throw new DonorNotFoundException("Donor not found with ID: " + dId);
		}

		donorService.delete(donor.get());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/donor/")
	public ResponseEntity<Donor> updateDonor(@Valid @RequestBody Donor donor) {
		Donor donorOld = donorService.findOneByEmail(donor.getEmail());
		if (donorOld == null) {
			throw new DonorNotFoundException("Donor not found with email: " + donor.getEmail());
		}
		donor.setDonor_id(donorOld.getDonor_id());
		donorService.save(donor);
		return ResponseEntity.ok().build();

	}
}