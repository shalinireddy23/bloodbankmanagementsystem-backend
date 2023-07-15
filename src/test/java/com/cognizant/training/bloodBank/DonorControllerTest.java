package com.cognizant.training.bloodBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.training.bloodBank.controller.DonorController;
import com.cognizant.training.bloodBank.exception.DonorNotFoundException;
import com.cognizant.training.bloodBank.model.Donor;
import com.cognizant.training.bloodBank.service.DonorService;

class DonorControllerTest {

    private DonorController donorController;
    private DonorService donorService;


    @Test
    void createDonor_shouldReturnDonor() {
        Donor donor = new Donor();
        when(donorService.save(donor)).thenReturn(donor);
        Donor result = donorController.createDonor(donor);
        verify(donorService).save(donor);
        assertEquals(donor, result);
    }

    @Test
    void getAllDonors_shouldReturnListOfDonors() {
        List<Donor> donorList = new ArrayList<>();
        when(donorService.findAll()).thenReturn(donorList);
        List<Donor> result = donorController.getAllDonors();
        verify(donorService).findAll();
        assertEquals(donorList, result);
    }

 

    @Test
    void deleteDonor_shouldDeleteDonorAndReturnOkResponse() {
        Long dId = 1L;
        Donor donor = new Donor();
        when(donorService.findOne(dId)).thenReturn(Optional.of(donor));
        doNothing().when(donorService).delete(donor);
        ResponseEntity<Donor> response = donorController.deleteDonor(dId);
        verify(donorService).findOne(dId);
        verify(donorService).delete(donor);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteDonor_shouldThrowDonorNotFoundException() {
        Long dId = 1L;
        when(donorService.findOne(dId)).thenReturn(Optional.empty());
        assertThrows(DonorNotFoundException.class, () -> donorController.deleteDonor(dId));
        verify(donorService).findOne(dId);
    }
//    @Test
//    void login_shouldReturnDonor() {
//        Donor donor = new Donor();
//        when(donorService.login(donor)).thenReturn(donor);
//        Donor result = donorController.login(donor);
//        verify(donorService).login(donor);
//        assertEquals(donor, result);
//    }
    
    @Test
    void updateDonor_shouldUpdateDonorAndReturnOkResponse() {
        Donor donor = new Donor();
        donor.setEmail("test@example.com");
        Donor oldDonor = new Donor();
        when(donorService.findOneByEmail(donor.getEmail())).thenReturn(oldDonor);
        when(donorService.save(donor)).thenReturn(donor);
        ResponseEntity<Donor> response = donorController.updateDonor(donor);
        verify(donorService).findOneByEmail(donor.getEmail());
        verify(donorService).save(donor);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateDonor_shouldThrowDonorNotFoundException() {
        Donor donor = new Donor();
        donor.setEmail("test@example.com");
        when(donorService.findOneByEmail(donor.getEmail())).thenReturn(null);
        assertThrows(DonorNotFoundException.class, () -> donorController.updateDonor(donor));
        verify(donorService).findOneByEmail(donor.getEmail());
    }
}