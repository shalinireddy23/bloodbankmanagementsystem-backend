package com.cognizant.training.bloodBank.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="blood_details")
public class BloodDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long blood_id;
	private String bloodGroup;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="bloodBankId", referencedColumnName="bloodBankId")
	private BloodBank bloodBank;

	public Long getBlood_id() {
		return blood_id;
	}

	public void setBlood_id(Long blood_id) {
		this.blood_id = blood_id;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public BloodDetails(String bloodGroup, int quantity, BloodBank bloodBank) {
		super();
		this.bloodGroup = bloodGroup;
		this.quantity = quantity;
		this.bloodBank = bloodBank;
	}

	public BloodDetails(Long blood_id, String bloodGroup, int quantity, BloodBank bloodBank) {
		super();
		this.blood_id = blood_id;
		this.bloodGroup = bloodGroup;
		this.quantity = quantity;
		this.bloodBank = bloodBank;
	}

	public BloodDetails() {
		super();
	}
	
}
