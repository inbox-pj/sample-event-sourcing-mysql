package com.usl.microservices.sampleeventsourcing.dto;

import java.util.Date;

public class CreateInsuranceRequest {

	private final String insuranceName;
	
	private final Date insuranceStartDate;
	
	private final int term;
	
	public CreateInsuranceRequest(String insuranceName, Date insuranceStartDate, int term) {
		this.insuranceName = insuranceName;
		this.insuranceStartDate = insuranceStartDate;
		this.term = term;
	}
	
	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public int getTerm() {
		return term;
	}
	
}
