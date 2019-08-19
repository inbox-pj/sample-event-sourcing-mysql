package com.usl.microservices.sampleeventsourcing.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usl.microservices.sampleeventsourcing.persistence.model.InsurancePersistenceEvent;

@Repository
public interface InsuranceEventDBRepository extends JpaRepository<InsurancePersistenceEvent, Integer> {
	
	public List<InsurancePersistenceEvent> findByInsuranceIdOrderByCreatedAtAsc(String insuranceId);

	public int countByInsuranceId(String insuranceId);

}
