package com.cognizant.training.bloodBank;

import com.cognizant.training.bloodBank.exception.BloodDetailsNotFoundException;
import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.model.BloodDetails;
import com.cognizant.training.bloodBank.model.UpdateQuantityClass;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.BloodDetailsRepository;
import com.cognizant.training.bloodBank.serviceImpl.BloodDetailsServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BloodDetailsServiceImplTest {

    @Mock
    private BloodDetailsRepository bloodDetailsRepository;

    @Mock
    private BloodBankRepository bloodBankRepository;

    @InjectMocks
    private BloodDetailsServiceImpl bloodDetailsService;

    @Test
    void save_ValidBloodDetails_ShouldSaveBloodDetails() {
        // Arrange
        BloodBank bloodBank = new BloodBank();
        bloodBank.setBloodBankId(1L);
        BloodDetails bloodDetails = new BloodDetails();
        bloodDetails.setBloodGroup("A+");
        bloodDetails.setQuantity(1);
        bloodDetails.setBloodBank(bloodBank);

        when(bloodDetailsRepository.findByBloodGroupAndBloodBankBloodBankId("A+", 1L))
                .thenReturn(Optional.empty());
        when(bloodBankRepository.findById(1L)).thenReturn(Optional.of(bloodBank));
        when(bloodDetailsRepository.save(bloodDetails)).thenReturn(bloodDetails);

        // Act
        BloodDetails savedBloodDetails = bloodDetailsService.save(bloodDetails);

        // Assert
        assertNotNull(savedBloodDetails);
        assertEquals(1, savedBloodDetails.getQuantity());
        verify(bloodDetailsRepository, times(1)).findByBloodGroupAndBloodBankBloodBankId("A+", 1L);
        verify(bloodBankRepository, times(1)).findById(1L);
        verify(bloodDetailsRepository, times(1)).save(bloodDetails);
    }

    @Test
    void save_InvalidBloodDetails_ShouldReturnNull() {
        // Arrange
        BloodDetails bloodDetails = new BloodDetails();
        bloodDetails.setBloodGroup("Invalid");
        // Act
        BloodDetails savedBloodDetails = bloodDetailsService.save(bloodDetails);
        // Assert
        assertNull(savedBloodDetails);
        verify(bloodDetailsRepository, never()).findByBloodGroupAndBloodBankBloodBankId(any(), any());
        verify(bloodBankRepository, never()).findById(anyLong());
        verify(bloodDetailsRepository, never()).save(any());
    }

    @Test
    void delete_ValidBloodDetails_ShouldDeleteBloodDetails() {
        // Arrange
        BloodDetails bloodDetails = new BloodDetails();
        // Act
        bloodDetailsService.delete(bloodDetails);
        // Assert
        verify(bloodDetailsRepository, times(1)).delete(bloodDetails);
    }

    @Test
    void findAll_ShouldReturnAllBloodDetails() {
        // Arrange
        // Act
        bloodDetailsService.findAll();
        // Assert
        verify(bloodDetailsRepository, times(1)).findAll();
    }

    @Test
    void findOne_ExistingBdId_ShouldReturnBloodDetailsOptional() {
        // Arrange
        Long bdId = 1L;
        BloodDetails bloodDetails = new BloodDetails();
        when(bloodDetailsRepository.findById(bdId)).thenReturn(Optional.of(bloodDetails));
        // Act
        Optional<BloodDetails> optionalBloodDetails = bloodDetailsService.findOne(bdId);
        // Assert
        assertTrue(optionalBloodDetails.isPresent());
        assertEquals(bloodDetails, optionalBloodDetails.get());
        verify(bloodDetailsRepository, times(1)).findById(bdId);
    }

    @Test
    void findOne_NonExistingBdId_ShouldReturnEmptyOptional() {
        // Arrange
        Long bdId = 1L;
        when(bloodDetailsRepository.findById(bdId)).thenReturn(Optional.empty());

        // Act
        Optional<BloodDetails> optionalBloodDetails = bloodDetailsService.findOne(bdId);

        // Assert
        assertFalse(optionalBloodDetails.isPresent());
        verify(bloodDetailsRepository, times(1)).findById(bdId);
    }

    @Test
    void getBloodBankByBloodGroup_ShouldReturnBloodDetailsList() {
        // Arrange
        String bloodGroup = "A+";
        // Act
        bloodDetailsService.getBloodBankByBloodGroup(bloodGroup);
        // Assert
        verify(bloodDetailsRepository, times(1)).findAllByBloodGroup(bloodGroup);
    }

    @Test
    void getBloodBankByLocation_ShouldReturnBloodDetailsList() {
        // Arrange
        String location = "Some Location";

        // Act
        bloodDetailsService.getBloodBankByLocation(location);
        // Assert
        // Add assertions for the expected behavior
    }

    @Test
    void getBloodDetailsById_ExistingBdId_ShouldReturnBloodDetails() {
        // Arrange
        Long bdId = 1L;
        BloodDetails bloodDetails = new BloodDetails();
        when(bloodDetailsRepository.findById(bdId)).thenReturn(Optional.of(bloodDetails));
        // Act
        BloodDetails result = bloodDetailsService.getBloodDetailsById(bdId);
        // Assert
        assertEquals(bloodDetails, result);
        verify(bloodDetailsRepository, times(1)).findById(bdId);
    }




    @Test
    void getBloodDetailsById_NonExistingBdId_ShouldThrowException() {
        // Arrange
        Long bdId = 1L;
        when(bloodDetailsRepository.findById(bdId)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(BloodDetailsNotFoundException.class,
                () -> bloodDetailsService.getBloodDetailsById(bdId));
        verify(bloodDetailsRepository, times(1)).findById(bdId);
    }




    @Test
    void updateQuantity_ValidUpdateQuantityClass_ShouldUpdateQuantity() {
        // Arrange
        UpdateQuantityClass updateQuantityClass = new UpdateQuantityClass();
        updateQuantityClass.setBloodBankId(1L);
        updateQuantityClass.setBloodGroup("A+");
        updateQuantityClass.setQuantity(1);
        BloodDetails bloodDetails = new BloodDetails();
        bloodDetails.setQuantity(10);
        when(bloodDetailsRepository.findByBloodBankBloodBankIdAndBloodGroup(1L, "A+")).thenReturn(bloodDetails);
        // Act
        bloodDetailsService.updateQuantity(updateQuantityClass);
        // Assert
        assertEquals(9, bloodDetails.getQuantity());
        verify(bloodDetailsRepository, times(1)).findByBloodBankBloodBankIdAndBloodGroup(1L, "A+");
        verify(bloodDetailsRepository, times(1)).save(bloodDetails);
    }
}
 