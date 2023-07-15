package com.cognizant.training.bloodBank;

import com.cognizant.training.bloodBank.model.Seeker;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.BloodDetailsRepository;
import com.cognizant.training.bloodBank.repository.SeekerRepository;
import com.cognizant.training.bloodBank.serviceImpl.SeekerServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeekerServiceImplTest {
    @Mock
    private SeekerRepository seekerRepository;
    @Mock
    private BloodBankRepository bloodBankRepository;
    @Mock
    private BloodDetailsRepository bloodDetailsRepository;
    @InjectMocks
    private SeekerServiceImpl seekerService;
    @Test
    void save_ValidSeeker_ShouldSaveSeeker() {
        // Arrange
        Seeker seeker = new Seeker();
        when(seekerRepository.save(seeker)).thenReturn(seeker);
        // Act
        Seeker savedSeeker = seekerService.save(seeker);
        // Assert
        assertNotNull(savedSeeker);
        assertEquals(seeker, savedSeeker);
        verify(seekerRepository, times(1)).save(seeker);
    }


    @Test
    void delete_ValidSeeker_ShouldDeleteSeeker() {
        // Arrange
        Seeker seeker = new Seeker();
        // Act
        seekerService.delete(seeker);
        // Assert
        verify(seekerRepository, times(1)).delete(seeker);
    }

    @Test
    void findAll_ShouldReturnAllSeekers() {
        // Arrange
        // Act
        List<Seeker> seekers = seekerService.findAll();
        // Assert
        assertNotNull(seekers);
        verify(seekerRepository, times(1)).findAll();
    }


    @Test
    void findOne_ExistingSid_ShouldReturnSeeker() {
        // Arrange
        Long sid = 1L;
        Seeker seeker = new Seeker();
        when(seekerRepository.findById(sid)).thenReturn(Optional.of(seeker));
        // Act
        Optional<Seeker> optionalSeeker = seekerService.findOne(sid);
        // Assert
        assertTrue(optionalSeeker.isPresent());
        assertEquals(seeker, optionalSeeker.get());
        verify(seekerRepository, times(1)).findById(sid);
    }

    
    @Test
    void findOne_NonExistingSid_ShouldReturnEmptyOptional() {
        // Arrange
        Long sid = 1L;
        when(seekerRepository.findById(sid)).thenReturn(Optional.empty());
        Optional<Seeker> optionalSeeker = seekerService.findOne(sid);
        assertFalse(optionalSeeker.isPresent());
        verify(seekerRepository, times(1)).findById(sid);
    }

//    @Test
//    void login_ValidSeeker_ShouldReturnTrue() {
//        // Arrange
//        Seeker seeker = new Seeker();
//        boolean result = seekerService.login(seeker);
//        assertFalse(result);
//    }
}
 