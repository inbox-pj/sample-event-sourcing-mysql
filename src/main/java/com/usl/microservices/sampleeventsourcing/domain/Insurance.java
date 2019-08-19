package com.usl.microservices.sampleeventsourcing.domain;

import java.util.Date;
import java.util.List;

import com.usl.microservices.sampleeventsourcing.event.InsuranceEvent;

public class Insurance {

	private String id;
	
	private String insuranceName;
	
	private Date insuranceStartDate;
	
	private Date insuranceEndDate = null;

	private String reason = null;
	
	private int term;
	
    private InsuranceState insuranceState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}

	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public void setInsuranceEndDate(Date insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}

	public InsuranceState getInsuranceState() {
		return insuranceState;
	}

	public void setInsuranceState(InsuranceState insuranceState) {
		this.insuranceState = insuranceState;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public Insurance(List<InsuranceEvent> events) {
        for (InsuranceEvent event : events) {
            applyEvent(event);
        }
    }

    public void applyEvent(InsuranceEvent event) {
        event.applyTo(this);
    }


    public boolean isActive() {
        return insuranceState.equals(InsuranceState.ACTIVE);
    }
}
