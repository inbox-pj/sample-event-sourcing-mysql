package com.usl.microservices.sampleeventsourcing.event;

import java.util.Calendar;
import java.util.Date;

import com.usl.microservices.sampleeventsourcing.domain.Insurance;
import com.usl.microservices.sampleeventsourcing.domain.InsuranceState;
import com.usl.microservices.sampleeventsourcing.dto.CreateInsuranceRequest;

public class InsuranceCreatedEvent extends InsuranceEvent {

	private static final Calendar cal = Calendar.getInstance();

	private String insuranceName;

	private Date insuranceStartDate;

	private int term;

	public InsuranceCreatedEvent() {
		
	}
	
	public InsuranceCreatedEvent(String insuranceId, CreateInsuranceRequest request) {
		this.insuranceId = insuranceId;
		this.insuranceName = request.getInsuranceName();
		this.insuranceStartDate = request.getInsuranceStartDate();
		this.term = request.getTerm();
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public int getTerm() {
		return term;
	}

	@Override
	public Insurance applyTo(Insurance insurance) {
		if (insurance.getId() != null) {
			throw new IllegalStateException("An Insurance plan with this ID already exists");
		}

		insurance.setId(this.insuranceId);
		insurance.setInsuranceName(this.insuranceName);
		insurance.setInsuranceState(InsuranceState.ACTIVE);
		insurance.setInsuranceStartDate(this.insuranceStartDate);

		cal.setTime(this.insuranceStartDate);
		cal.add(Calendar.YEAR, this.term);

		insurance.setInsuranceEndDate(cal.getTime());
		insurance.setTerm(this.term);
		return insurance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((insuranceName == null) ? 0 : insuranceName.hashCode());
		result = prime * result + ((insuranceStartDate == null) ? 0 : insuranceStartDate.hashCode());
		result = prime * result + term;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InsuranceCreatedEvent other = (InsuranceCreatedEvent) obj;
		if (insuranceName == null) {
			if (other.insuranceName != null)
				return false;
		} else if (!insuranceName.equals(other.insuranceName))
			return false;
		if (insuranceStartDate == null) {
			if (other.insuranceStartDate != null)
				return false;
		} else if (!insuranceStartDate.equals(other.insuranceStartDate))
			return false;
		if (term != other.term)
			return false;
		return true;
	}

}
