package com.usl.microservices.sampleeventsourcing.persistence;

import java.util.List;

import com.usl.microservices.sampleeventsourcing.event.InsuranceEvent;

public interface InsuranceEventRepository {

	void add(InsuranceEvent event);

	List<InsuranceEvent> getByInsuranceId(String insurance_id);

}
