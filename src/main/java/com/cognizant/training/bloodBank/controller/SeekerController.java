package com.cognizant.training.bloodBank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognizant.training.bloodBank.exception.SeekerNotFoundException;
import com.cognizant.training.bloodBank.model.Login;
import com.cognizant.training.bloodBank.model.Seeker;
import com.cognizant.training.bloodBank.service.SeekerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class SeekerController {

	@Autowired
	SeekerService seekerService;
	 static Long curr_seeker;


	@PostMapping("/seeker")
	public Seeker createSeeker(@Valid @RequestBody Seeker seeker) {
		
		return seekerService.save(seeker);
	}



	@GetMapping("/getseeker")
	public List<Seeker> getAllSeekers() {
		System.out.println("getAll");
		return seekerService.findAll();
	}

	@GetMapping("/seeker/id/{id}")
	public ResponseEntity<Seeker> getSeekerById(@PathVariable(value = "id") Long seekerId) {
		Optional<Seeker> seeker = seekerService.findOne(seekerId);
		if (!seeker.isPresent()) {
			throw new SeekerNotFoundException("Seeker not found with ID: " + seekerId);
		}
		return ResponseEntity.ok(seeker.get());
	}

	@DeleteMapping("/seeker/{id}")
    public ResponseEntity<Seeker> deleteSeeker(@PathVariable(value = "id") Long sId) {
        Optional<Seeker> seeker = seekerService.findOne(sId);
        if (!seeker.isPresent()) {
            throw new SeekerNotFoundException("Seeker not found with ID: " + sId);
        }

        seekerService.delete(seeker.get());

        return ResponseEntity.ok().build();
    }
	

	@PostMapping("/seeker/login")
	public Seeker LoginSeeker(@RequestBody Login login) {
		Seeker obj= seekerService.login(login);
		curr_seeker=obj.getSeeker_id();
		return obj;
	}
	
	@PostMapping("/seeker/logout")
	public Long LogoutSeeker() {
		curr_seeker=0L;
		return curr_seeker;
	}

	@PutMapping("/seeker/")
    public ResponseEntity<Seeker> updateSeeker(@Valid @RequestBody Seeker seeker) {
        Optional<Seeker> seekerOld = seekerService.findOne(seeker.getSeeker_id());
        if (!seekerOld.isPresent()) {
            throw new SeekerNotFoundException("Seeker not found with ID: " + seeker.getSeeker_id());
        }
		seeker.setSeeker_id(seekerOld.get().getSeeker_id());
		
		seekerService.save(seeker);
		 return ResponseEntity.ok().build();

	}
}
