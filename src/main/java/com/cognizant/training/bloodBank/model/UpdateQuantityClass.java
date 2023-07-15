package com.cognizant.training.bloodBank.model;

public class UpdateQuantityClass {

	private Long bloodBankId;
	private String bloodGroup;
	private int quantity;
	public Long getBloodBankId() {
		return bloodBankId;
	}
	public void setBloodBankId(Long bloodBankId) {
		this.bloodBankId = bloodBankId;
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
	public UpdateQuantityClass(Long bloodBankId, String bloodGroup, int quantity) {
		super();
		this.bloodBankId = bloodBankId;
		this.bloodGroup = bloodGroup;
		this.quantity = quantity;
	}
	public UpdateQuantityClass() {
		super();
	}
	
}
