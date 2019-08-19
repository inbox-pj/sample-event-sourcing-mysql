package com.usl.microservices.sampleeventsourcing.dto;

public class ModifyInsuranceRequest {

	private int term;

	public ModifyInsuranceRequest() {

	}

	public ModifyInsuranceRequest(int term) {
		this.term = term;
	}

	public int getTerm() {
		return term;
	}
	
}
