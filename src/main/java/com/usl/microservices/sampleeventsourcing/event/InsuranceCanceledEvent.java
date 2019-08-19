package com.usl.microservices.sampleeventsourcing.event;

import java.util.Date;

import com.usl.microservices.sampleeventsourcing.domain.Insurance;
import com.usl.microservices.sampleeventsourcing.domain.InsuranceState;
import com.usl.microservices.sampleeventsourcing.dto.CancelInsuranceRequest;

public class InsuranceCanceledEvent extends InsuranceEvent {

	private Date insuranceEndDate;

	private String reason;

	public InsuranceCanceledEvent() {
		
	}
	
	public InsuranceCanceledEvent(String insuranceId, CancelInsuranceRequest request) {
		this.insuranceId = insuranceId;
		this.insuranceEndDate = request.getInsuranceEndDate();
		this.reason = request.getReason();
	}

	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public String getReason() {
		return reason;
	}
	
	@Override
	public Insurance applyTo(Insurance insurance) {
		if (insurance.getId() == null) {
            throw new IllegalStateException("No insurance plan found");
        }

        if (insurance.getInsuranceState() != InsuranceState.ACTIVE) {
            throw new IllegalStateException("Only active insurances can be canceled");
        }

        if (insuranceEndDate.before(insurance.getInsuranceStartDate())) {
            throw new IllegalArgumentException("insurance end date may not before start date");
        }

        insurance.setInsuranceState(InsuranceState.CANCELLED);
        insurance.setInsuranceEndDate(this.insuranceEndDate);
        insurance.setReason(this.reason);
        return insurance;
	}
	
}
