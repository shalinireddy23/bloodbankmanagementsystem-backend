package com.cognizant.training.bloodBank;

import com.cognizant.training.bloodBank.controller.SeekerController;
import com.cognizant.training.bloodBank.exception.SeekerNotFoundException;
import com.cognizant.training.bloodBank.model.Seeker;
import com.cognizant.training.bloodBank.service.SeekerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeekerControllerTest {

	@Mock
	private SeekerService seekerService;

	@InjectMocks
	private SeekerController seekerController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateSeeker() {
		Seeker seeker = new Seeker();
		seeker.setSeeker_id(1L);
		seeker.setName("John");
		when(seekerService.save(seeker)).thenReturn(seeker);
		Seeker savedSeeker = seekerController.createSeeker(seeker);
		assertEquals(seeker, savedSeeker);
		verify(seekerService, times(1)).save(any(Seeker.class));
	}

	@Test
	void testGetAllSeekers() {
		List<Seeker> seekers = new ArrayList<>();
		when(seekerService.findAll()).thenReturn(seekers);
		List<Seeker> allSeekers = seekerController.getAllSeekers();
		assertEquals(seekers, allSeekers);
		verify(seekerService, times(1)).findAll();
	}

	@Test
	void testDeleteSeeker_Success() {
		Long seekerId = 1L;
		Seeker seeker = new Seeker();
		seeker.setSeeker_id(seekerId);
		Optional<Seeker> optionalSeeker = Optional.of(seeker);
		when(seekerService.findOne(seekerId)).thenReturn(optionalSeeker);
		ResponseEntity<Seeker> response = seekerController.deleteSeeker(seekerId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(seekerService, times(1)).delete(seeker);
	}

	@Test
	void testDeleteSeeker_NotFound() {
		Long seekerId = 1L;
		Optional<Seeker> optionalSeeker = Optional.empty();
		when(seekerService.findOne(seekerId)).thenReturn(optionalSeeker);
		assertThrows(SeekerNotFoundException.class, () -> seekerController.deleteSeeker(seekerId));
		verify(seekerService, never()).delete(any(Seeker.class));
	}

	@Test
	void testUpdateSeeker_Success() {
		Long seekerId = 1L;
		Seeker seeker = new Seeker();
		seeker.setSeeker_id(seekerId);
		Optional<Seeker> optionalSeeker = Optional.of(seeker);
		when(seekerService.findOne(seekerId)).thenReturn(optionalSeeker);
		ResponseEntity<Seeker> response = seekerController.updateSeeker(seeker);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(seekerService, times(1)).save(seeker);
	}
}