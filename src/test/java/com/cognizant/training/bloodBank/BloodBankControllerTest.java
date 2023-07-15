package com.cognizant.training.bloodBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.training.bloodBank.controller.BloodBankController;
import com.cognizant.training.bloodBank.exception.BloodBankNotFoundException;
import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.service.BloodBankService;

class BloodBankControllerTest {

    private BloodBankController bloodBankController;
    private BloodBankService bloodBankService; 

    @Test
    void createBloodBank_shouldReturnBloodBank() {
        BloodBank bloodBank = new BloodBank();
        when(bloodBankService.save(any(BloodBank.class))).thenReturn(bloodBank);
        BloodBank result = bloodBankController.createBloodBank(bloodBank);
        verify(bloodBankService).save(bloodBank);
        assertEquals(bloodBank, result);
    }

    @Test
    void getAllBloodBanks_shouldReturnListOfBloodBanks() {
        List<BloodBank> bloodBanks = new ArrayList<>();
        when(bloodBankService.findAll()).thenReturn(bloodBanks);
        List<BloodBank> result = bloodBankController.getAllBloodBanks();
        verify(bloodBankService).findAll();
        assertEquals(bloodBanks, result);
    }

    @Test
    void deleteBloodBank_shouldDeleteBloodBankAndReturnOkResponse() {
        String email = "bloodbank@example.com";
        BloodBank bloodBank = new BloodBank();
        when(bloodBankService.findOneByEmail(email)).thenReturn(bloodBank);
        doNothing().when(bloodBankService).deleteByEmail(email);
        ResponseEntity<BloodBank> response = bloodBankController.deleteBloodBank(email);
        verify(bloodBankService).findOneByEmail(email);
        verify(bloodBankService).deleteByEmail(email);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteBloodBank_shouldThrowBloodBankNotFoundException() {
        String email = "bloodbank@example.com";
        when(bloodBankService.findOneByEmail(email)).thenReturn(null);
        assertThrows(BloodBankNotFoundException.class, () -> bloodBankController.deleteBloodBank(email));
        verify(bloodBankService).findOneByEmail(email);
    }

    @Test
    void login_shouldReturnBloodBank() {
        BloodBank bloodBank = new BloodBank();
        when(bloodBankService.login(any(BloodBank.class))).thenReturn(bloodBank);
        BloodBank result = bloodBankController.login(bloodBank);
        verify(bloodBankService).login(bloodBank);
        assertEquals(bloodBank, result);
    }

    @Test
    void getBloodBankByLocation_shouldReturnListOfBloodBanks() {
        String location = "New York";
        List<BloodBank> bloodBanks = new ArrayList<>();
        when(bloodBankService.getBloodBankByLocation(location)).thenReturn(bloodBanks);
        List<BloodBank> result = bloodBankController.getBloodBankByLocation(location);
        verify(bloodBankService).getBloodBankByLocation(location);
        assertEquals(bloodBanks, result);
    }

    @Test
    void updateBloodBank_shouldUpdateBloodBankAndReturnOkResponse() {
        BloodBank bloodBank = new BloodBank();
        BloodBank oldBloodBank = new BloodBank();
        when(bloodBankService.findOneByEmail(bloodBank.getEmail())).thenReturn(oldBloodBank);
        when(bloodBankService.save(bloodBank)).thenReturn(bloodBank);
        ResponseEntity<BloodBank> response = bloodBankController.updateBloodBank(bloodBank);
        verify(bloodBankService).findOneByEmail(bloodBank.getEmail());
        verify(bloodBankService).save(bloodBank);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateBloodBank_shouldThrowBloodBankNotFoundException() {
        BloodBank bloodBank = new BloodBank();
        when(bloodBankService.findOneByEmail(bloodBank.getEmail())).thenReturn(null);
        assertThrows(BloodBankNotFoundException.class, () -> bloodBankController.updateBloodBank(bloodBank));
        verify(bloodBankService).findOneByEmail(bloodBank.getEmail());
    }
}