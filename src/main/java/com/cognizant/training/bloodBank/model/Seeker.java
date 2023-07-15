package com.cognizant.training.bloodBank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="seeker")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Seeker {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long seeker_id;
	@NotNull
	private String name;
	@NotNull
	@Email
	@Column(unique = true)
	private String email;
	@NotNull
	@Column(unique = true)
	@Size(min=10, max=10)
	private String contact_number;
	@NotNull
	private String location;
	@NotNull
	private String blood_group;
	@NotNull
	private int quantity;
	@NotNull
	private String gender;
	@NotNull
	private int age;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getSeeker_id() {
		return seeker_id;
	}
	public void setSeeker_id(Long seeker_id) {
		this.seeker_id = seeker_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getBlood_group() {
		return blood_group;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
