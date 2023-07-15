package com.cognizant.training.bloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="blood_bank")
public class BloodBank {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bloodBankId;
	@NotNull
	@Size(min=3, max=20)
	@Column(unique=true)
	private String name;
	@NotNull
	@Size(min=10, max=10)
	private String contact_number;
	@NotNull
	private String location;
	private String address;
	@NotNull
	@Email
	private String email;
	
	public Long getBloodBankId() {
		return bloodBankId;
	}
	public void setBloodBankId(Long bloodBankId) {
		this.bloodBankId = bloodBankId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
