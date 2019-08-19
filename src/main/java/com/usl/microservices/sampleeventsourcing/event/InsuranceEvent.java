package com.usl.microservices.sampleeventsourcing.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.usl.microservices.sampleeventsourcing.domain.Insurance;

@JsonTypeInfo(use = Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@Type(value = InsuranceCreatedEvent.class),
		@Type(value = InsuranceModifiedEvent.class),
		@Type(value = InsuranceCanceledEvent.class),
})
public abstract class InsuranceEvent {

	protected String insuranceId;

	public InsuranceEvent() {
		
	}
	
	public InsuranceEvent(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	public String getInsuranceId() {
		return insuranceId;
	}

	public abstract Insurance applyTo(Insurance insurance);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((insuranceId == null) ? 0 : insuranceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InsuranceEvent other = (InsuranceEvent) obj;
		if (insuranceId == null) {
			if (other.insuranceId != null)
				return false;
		} else if (!insuranceId.equals(other.insuranceId))
			return false;
		return true;
	};
    
    
}
