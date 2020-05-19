package com.celestino.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Patient {

	private long id;
	private String patientName;
	private String gender;
	private Date bornDate;
	private String marital_status;
	private String docType;
	private String docNumber;
	private String email;
	private String phoneNumber;
	private String nationality;
	private String address;
	private Integer postal_code;
	private String country;
	private Timestamp createdAt;

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getAddress() {
		return address;
	}

	public Integer getPostal_code() {
		return postal_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostal_code(Integer postal_code) {
		this.postal_code = postal_code;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Patient patient = (Patient) o;
		return id == patient.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Patient{" + "id=" + id + ", patientName='" + patientName + '\'' + ", gender='" + gender + '\''
				+ ", bornDate=" + bornDate + ", marital_status='" + marital_status + '\'' + ", docType='" + docType
				+ '\'' + ", docNumber='" + docNumber + '\'' + ", email='" + email + '\'' + ", phoneNumber='"
				+ phoneNumber + '\'' + ", nationality='" + nationality + '\'' + ", address='" + address + '\''
				+ ", postal_code=" + postal_code + ", country='" + country + '\'' + ", createdAt=" + createdAt + '}';
	}
}
