package com.usl.microservices.sampleeventsourcing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.JdkIdGenerator;

import com.usl.microservices.sampleeventsourcing.domain.Insurance;
import com.usl.microservices.sampleeventsourcing.dto.CancelInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.dto.CreateInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.dto.ModifyInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.event.InsuranceCanceledEvent;
import com.usl.microservices.sampleeventsourcing.event.InsuranceCreatedEvent;
import com.usl.microservices.sampleeventsourcing.event.InsuranceEvent;
import com.usl.microservices.sampleeventsourcing.event.InsuranceModifiedEvent;
import com.usl.microservices.sampleeventsourcing.persistence.InsuranceEventRepository;
import com.usl.microservices.sampleeventsourcing.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private JdkIdGenerator idGenerator;

	@Autowired
	private InsuranceEventRepository repository;

	@Override
	public Insurance createInsurance(CreateInsuranceRequest request) {
		String id = idGenerator.generateId().toString();

		InsuranceEvent event = new InsuranceCreatedEvent(id, request);
		return applyEvent(id, event);
	}

	@Override
	public Insurance modifyInsurance(String insuranceId, ModifyInsuranceRequest request) {
		InsuranceEvent event = new InsuranceModifiedEvent(insuranceId, request);
		return applyEvent(insuranceId, event);
	}

	@Override
	public Insurance cancelInsurance(String insuranceId, CancelInsuranceRequest request) {
		InsuranceEvent event = new InsuranceCanceledEvent(insuranceId, request);
		return applyEvent(insuranceId, event);
	}

	@Override
	public Insurance getInsurance(String insuranceId) {
		List<InsuranceEvent> events = repository.getByInsuranceId(insuranceId);

		if(events == null || events.size() == 0) {
			return null;
		}

		return new Insurance(events);
	}

	private Insurance applyEvent(String id, InsuranceEvent event) {
		List<InsuranceEvent> events = repository.getByInsuranceId(id);
		Insurance insurance = new Insurance(events);

		insurance.applyEvent(event);

		repository.add(event);
		return insurance;
	}
}
