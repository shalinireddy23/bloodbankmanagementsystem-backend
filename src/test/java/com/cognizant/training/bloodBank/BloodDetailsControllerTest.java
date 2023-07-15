package com.cognizant.training.bloodBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.training.bloodBank.controller.BloodDetailsController;
import com.cognizant.training.bloodBank.exception.BloodDetailsNotFoundException;
import com.cognizant.training.bloodBank.model.BloodDetails;
import com.cognizant.training.bloodBank.service.BloodDetailsService;

 

class BloodDetailsControllerTest {
    private BloodDetailsController bloodDetailsController;
    private BloodDetailsService bloodDetailsService; 

    @Test
    void createBloodDetails_shouldReturnBloodDetails() {
        BloodDetails bloodDetails = new BloodDetails();
        when(bloodDetailsService.save(any(BloodDetails.class))).thenReturn(bloodDetails);
        BloodDetails result = bloodDetailsController.createBloodDetails(bloodDetails);
        verify(bloodDetailsService).save(bloodDetails);
        assertEquals(bloodDetails, result);
    }

    @Test
    void getBloodBankByLocation_shouldReturnListOfBloodDetails() {
        String location = "New York";
        List<BloodDetails> bloodDetailsList = new ArrayList<>();
        when(bloodDetailsService.getBloodBankByLocation(location)).thenReturn(bloodDetailsList);
        List<BloodDetails> result = bloodDetailsController.getBloodBankByLocation(location);
        verify(bloodDetailsService).getBloodBankByLocation(location);
        assertEquals(bloodDetailsList, result);
    }

 

    @Test
    void getAllBloodDetails_shouldReturnListOfBloodDetails() {
        List<BloodDetails> bloodDetailsList = new ArrayList<>();
        when(bloodDetailsService.findAll()).thenReturn(bloodDetailsList);
        List<BloodDetails> result = bloodDetailsController.getAllBloodDetails();
        verify(bloodDetailsService).findAll();
        assertEquals(bloodDetailsList, result);
    }

    @Test
    void deleteBloodDetails_shouldDeleteBloodDetailsAndReturnOkResponse() {
        Long bdId = 1L;
        BloodDetails bloodDetails = new BloodDetails();
        when(bloodDetailsService.findOne(bdId)).thenReturn(Optional.of(bloodDetails));
        doNothing().when(bloodDetailsService).delete(bloodDetails);
        ResponseEntity<BloodDetails> response = bloodDetailsController.deleteBloodDetails(bdId);
        verify(bloodDetailsService).findOne(bdId);
        verify(bloodDetailsService).delete(bloodDetails);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

 

    @Test
    void deleteBloodDetails_shouldThrowBloodDetailsNotFoundException() {
        Long bdId = 1L;
        when(bloodDetailsService.findOne(bdId)).thenReturn(Optional.empty());
        assertThrows(BloodDetailsNotFoundException.class, () -> bloodDetailsController.deleteBloodDetails(bdId));
        verify(bloodDetailsService).findOne(bdId);
    }

 

    @Test
    void updateBloodDetails_shouldUpdateBloodDetailsAndReturnOkResponse() {
        BloodDetails bloodDetails = new BloodDetails();
        bloodDetails.setBlood_id(1L);
        BloodDetails oldBloodDetails = new BloodDetails();
        when(bloodDetailsService.findOne(bloodDetails.getBlood_id())).thenReturn(Optional.of(oldBloodDetails));
        when(bloodDetailsService.save(bloodDetails)).thenReturn(bloodDetails);
        ResponseEntity<BloodDetails> response = bloodDetailsController.updateBloodDetails(bloodDetails);
        verify(bloodDetailsService).findOne(bloodDetails.getBlood_id());
        verify(bloodDetailsService).save(bloodDetails);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

 

    @Test
    void updateBloodDetails_shouldThrowBloodDetailsNotFoundException() {
        BloodDetails bloodDetails = new BloodDetails();
        bloodDetails.setBlood_id(1L);
        when(bloodDetailsService.findOne(bloodDetails.getBlood_id())).thenReturn(Optional.empty());
        assertThrows(BloodDetailsNotFoundException.class, () -> bloodDetailsController.updateBloodDetails(bloodDetails));
        verify(bloodDetailsService).findOne(bloodDetails.getBlood_id());
    }
}