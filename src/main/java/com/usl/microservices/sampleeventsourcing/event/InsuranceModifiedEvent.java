package com.usl.microservices.sampleeventsourcing.event;

import com.usl.microservices.sampleeventsourcing.domain.Insurance;
import com.usl.microservices.sampleeventsourcing.domain.InsuranceState;
import com.usl.microservices.sampleeventsourcing.dto.ModifyInsuranceRequest;

import java.util.Calendar;

public class InsuranceModifiedEvent extends InsuranceEvent {

	private static final Calendar cal = Calendar.getInstance();

	private int term;

	public InsuranceModifiedEvent() {

	}

	public InsuranceModifiedEvent(String insuranceId, ModifyInsuranceRequest request) {
		this.insuranceId = insuranceId;
		this.term = request.getTerm();
	}

	public int getTerm() {
		return term;
	}

	@Override
	public Insurance applyTo(Insurance insurance) {
		if (insurance.getId() == null) {
			throw new IllegalStateException("No insurance found");
		}

		if (insurance.getInsuranceState() != InsuranceState.ACTIVE) {
			throw new IllegalStateException("Only active insurances can be modified");
		}

		insurance.setTerm(this.term);

		cal.setTime(insurance.getInsuranceStartDate());
		cal.add(Calendar.YEAR, this.term);
		insurance.setInsuranceEndDate(cal.getTime());

		return insurance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		InsuranceModifiedEvent other = (InsuranceModifiedEvent) obj;
		if (term != other.term)
			return false;
		return true;
	}

}
