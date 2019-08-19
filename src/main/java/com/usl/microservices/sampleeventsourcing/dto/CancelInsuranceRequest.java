package com.usl.microservices.sampleeventsourcing.dto;

import java.util.Date;

public class CancelInsuranceRequest {

	private final Date insuranceEndDate;
	
	private final String reason;
	
	public CancelInsuranceRequest(Date insuranceEndDate, String reason) {
		this.insuranceEndDate = insuranceEndDate;
		this.reason = reason;
	}

	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public String getReason() {
		return reason;
	}
	
}
