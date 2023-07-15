package com.cognizant.training.bloodBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.training.bloodBank.model.BloodBank;
import com.cognizant.training.bloodBank.repository.BloodBankRepository;
import com.cognizant.training.bloodBank.repository.DonorRepository;
import com.cognizant.training.bloodBank.serviceImpl.BloodBankServiceImpl;

 class BloodBankServiceImplTest {

  @MockBean
  private BloodBankRepository bloodBankRepository;

  @MockBean
  private DonorRepository donorRepository;

 
  @InjectMocks
  private BloodBankServiceImpl bloodBankService;

  @Test
  void testDeleteBloodBankByEmail() {
    String email = "test@example.com";
    when(bloodBankRepository.deleteByEmail(email)).thenReturn(1);
    int deletedCount = bloodBankService.deleteByEmail(email);
    assertEquals(1, deletedCount);
    verify(bloodBankRepository).deleteByEmail(email);
  }


  @Test
  void testFindAllBloodBanks() {
    List<BloodBank> bloodBanks = new ArrayList<>();
    bloodBanks.add(new BloodBank());
    when(bloodBankRepository.findAll()).thenReturn(bloodBanks);
    List<BloodBank> retrievedBloodBanks = bloodBankService.findAll();
    assertEquals(1, retrievedBloodBanks.size());
    verify(bloodBankRepository).findAll();
  }

 

  @Test
  void testFindOneBloodBankByEmail() {
    String email = "test@example.com";
    BloodBank bloodBank = new BloodBank();
    when(bloodBankRepository.findOneByEmail(email)).thenReturn(bloodBank);
    BloodBank retrievedBloodBank = bloodBankService.findOneByEmail(email);
    assertEquals(bloodBank, retrievedBloodBank);
    verify(bloodBankRepository).findOneByEmail(email);
  }

 

  @Test
  void testFindOneBloodBank() {
    Long id = 1L;
    Optional<BloodBank> bloodBankOptional = Optional.of(new BloodBank());
    when(bloodBankRepository.findById(id)).thenReturn(bloodBankOptional);
    Optional<BloodBank> retrievedBloodBank = bloodBankService.findOne(id);
    assertTrue(retrievedBloodBank.isPresent());
    assertEquals(bloodBankOptional.get(), retrievedBloodBank.get());
    verify(bloodBankRepository).findById(id);
  }

 
  @Test
  void testFindOneBloodBank_NotFound() {
    Long id = 1L;
    when(bloodBankRepository.findById(id)).thenReturn(Optional.empty());
    Optional<BloodBank> retrievedBloodBank = bloodBankService.findOne(id);
    assertFalse(retrievedBloodBank.isPresent());
    verify(bloodBankRepository).findById(id);
  }


  @Test
  void testGetBloodBankByLocation() {
    String location = "City";
    List<BloodBank> bloodBanks = new ArrayList<>();
    bloodBanks.add(new BloodBank());
    when(bloodBankRepository.getBloodBankByLocation(location)).thenReturn(bloodBanks);
    List<BloodBank> retrievedBloodBanks = bloodBankService.getBloodBankByLocation(location);
    assertEquals(1, retrievedBloodBanks.size());
    verify(bloodBankRepository).getBloodBankByLocation(location);
  }
}