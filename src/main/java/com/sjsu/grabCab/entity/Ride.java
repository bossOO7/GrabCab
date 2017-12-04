package com.sjsu.grabCab.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Ride {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rideId;
	
	private Date startTime;
	
	private Date endTime;
	
	private String pickUpLocation;
	
	private String dropOffLocation;
	
	private double cost;
	
	private String carType;
	
	private String reason;
	
	private String rideStatus;
	
	private String driverRating;
	
	private String passengerRating;
	
	public Ride(){
		
	}
	
	
	
	public Ride(Long rideId, Date startTime, Date endTime, String pickUpLocation, String dropOffLocation, double cost,
			String carType, String reason, String rideStatus, String driverRating, String passengerRating,
			String licenseNumber, String email) {
		super();
		this.rideId = rideId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.cost = cost;
		this.carType = carType;
		this.reason = reason;
		this.rideStatus = rideStatus;
		this.driverRating = driverRating;
		this.passengerRating = passengerRating;
		this.licenseNumber = licenseNumber;
		this.email = email;
	}

	public Long getId() {
		return rideId;
	}

	public void setId(Long id) {
		this.rideId = rideId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPickUpLocation() {
		return pickUpLocation;
	}

	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	public String getDropOffLocation() {
		return dropOffLocation;
	}

	public void setDropOffLocation(String dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}

	public double getPrice() {
		return cost;
	}

	public void setPrice(double cost) {
		this.cost = cost;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}

	public String getDriverRating() {
		return driverRating;
	}

	public void setDriverRating(String driverRating) {
		this.driverRating = driverRating;
	}

	public String getPassengerRating() {
		return passengerRating;
	}

	public void setPassengerRating(String passengerRating) {
		this.passengerRating = passengerRating;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String licenseNumber;
	
	private String email;
}
