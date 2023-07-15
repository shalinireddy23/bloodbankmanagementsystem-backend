package com.cognizant.training.bloodBank;

import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.model.BloodDetails;
import com.cognizant.training.bloodBank.model.Donor;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.BloodDetailsRepository;
import com.cognizant.training.bloodBank.repository.DonorRepository;
import com.cognizant.training.bloodBank.serviceImpl.DonorServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonorServiceImplTest {

    @Mock
    private BloodBankRepository bloodBankRepository;
    @Mock
    private DonorRepository donorRepository;   
    @Mock
    private BloodDetailsRepository bloodDetailsRepository;

    @InjectMocks
    private DonorServiceImpl donorService;
    
    @Test
    void save_ValidDonor_ShouldSaveDonorAndBloodDetails() {
        // Arrange
        BloodBank bloodBank = new BloodBank();
        bloodBank.setBloodBankId(1L);
        Donor donor = new Donor();
        donor.setEmail("test@example.com");
        donor.setBlood_group("A+");
        donor.setQuantity(1);
        donor.setBloodBank(bloodBank);
        BloodDetails bloodDetails = new BloodDetails();
        bloodDetails.setBloodGroup("A+");
        bloodDetails.setQuantity(1);
        bloodDetails.setBloodBank(bloodBank);
        when(bloodBankRepository.findById(1L)).thenReturn(Optional.of(bloodBank));
        when(bloodDetailsRepository.findByBloodGroupAndBloodBankBloodBankId("A+", 1L)).thenReturn(Optional.of(bloodDetails));
        when(bloodDetailsRepository.save(any(BloodDetails.class))).thenReturn(bloodDetails);
        when(donorRepository.save(donor)).thenReturn(donor);
        // Act
        Donor savedDonor = donorService.save(donor);
        // Assert
        assertNotNull(savedDonor);
        assertEquals("test@example.com", savedDonor.getEmail());
        assertEquals("A+", savedDonor.getBlood_group());
        assertEquals(1, savedDonor.getQuantity());
        verify(bloodBankRepository, times(1)).findById(1L);
        verify(bloodDetailsRepository, times(1)).findByBloodGroupAndBloodBankBloodBankId("A+", 1L);
        verify(bloodDetailsRepository, times(1)).save(bloodDetails);
        verify(donorRepository, times(1)).save(donor);
    }




    @Test
    void delete_ValidDonor_ShouldDeleteDonor() {
        // Arrange
        Donor donor = new Donor();
        // Act
        donorService.delete(donor);
        // Assert
        verify(donorRepository, times(1)).delete(donor);
    }




    @Test
    void findOneByEmail_ExistingEmail_ShouldReturnDonor() {
        // Arrange
        String email = "test@example.com";
        Donor donor = new Donor();
        when(donorRepository.findOneByEmail(email)).thenReturn(donor);
        // Act
        Donor result = donorService.findOneByEmail(email);
        // Assert
        assertEquals(donor, result);
        verify(donorRepository, times(1)).findOneByEmail(email);
    }




    @Test
    void findAll_ShouldReturnAllDonors() {
        // Arrange
        // Act
        donorService.findAll();
        // Assert
        verify(donorRepository, times(1)).findAll();
    }




    @Test
    void findOne_ExistingDid_ShouldReturnDonor() {
        // Arrange
        Long did = 1L;
        Donor donor = new Donor();
        when(donorRepository.findById(did)).thenReturn(Optional.of(donor));
        // Act
        Optional<Donor> optionalDonor = donorService.findOne(did);
        assertTrue(optionalDonor.isPresent());
        assertEquals(donor, optionalDonor.get());
        verify(donorRepository, times(1)).findById(did);
    }




    @Test
    void findOne_NonExistingDid_ShouldReturnEmptyOptional() {
        // Arrange
        Long did = 1L;
        when(donorRepository.findById(did)).thenReturn(Optional.empty());
        Optional<Donor> optionalDonor = donorService.findOne(did);
        assertFalse(optionalDonor.isPresent());
        verify(donorRepository, times(1)).findById(did);
    }




    @Test
    void getDonorByLocation_ShouldReturnDonorList() {
        // Arrange
        String location = "Some Location";
        donorService.getDonorByLocation(location);
    }
}
 