package com.usl.microservices.sampleeventsourcing.persistence.impl;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usl.microservices.sampleeventsourcing.event.InsuranceEvent;
import com.usl.microservices.sampleeventsourcing.persistence.InsuranceEventDBRepository;
import com.usl.microservices.sampleeventsourcing.persistence.InsuranceEventRepository;
import com.usl.microservices.sampleeventsourcing.persistence.model.InsurancePersistenceEvent;

@Component
public class InsuranceEventRepositoryImpl implements InsuranceEventRepository {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private InsuranceEventDBRepository repository;

	@Override
	public void add(InsuranceEvent event) {
		InsurancePersistenceEvent persistenceEvent = createPersistentEvent(event);
		repository.save(persistenceEvent);
	}

	@Override
	public List<InsuranceEvent> getByInsuranceId(String insuranceId) {
		return eventsOfInsurance(insuranceId);
	}

	private List<InsuranceEvent> eventsOfInsurance(String insuranceId) {
		if(repository.countByInsuranceId(insuranceId) == 0) {
			return Collections.emptyList();
		}

		List<InsurancePersistenceEvent> persistentEvents = repository.findByInsuranceIdOrderByCreatedAtAsc(insuranceId);
		return mapToPolicyEvents(persistentEvents);
		
	}

	private List<InsuranceEvent> mapToPolicyEvents(List<InsurancePersistenceEvent> persistentEvents) {
		return persistentEvents
				.stream()
				.sorted(Comparator.comparing(InsurancePersistenceEvent::getCreatedAt))
				.map(this::mapToInsuranceEvent)
				.collect(toList());
	}

	private InsuranceEvent mapToInsuranceEvent(InsurancePersistenceEvent persistentEvent) {
		try {
			return objectMapper.readValue(persistentEvent.getContent(), InsuranceEvent.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private InsurancePersistenceEvent createPersistentEvent(InsuranceEvent event) {
		try {
			InsurancePersistenceEvent persistenceEvent = new InsurancePersistenceEvent();
			persistenceEvent.setInsuranceId(event.getInsuranceId());
			persistenceEvent.setContent(objectMapper.writeValueAsString(event));
			persistenceEvent.setCreatedAt(new Date());
			return persistenceEvent;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
