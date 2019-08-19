package com.usl.microservices.sampleeventsourcing.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insurance_events")
public class InsurancePersistenceEvent {

	@Id
	@Column(name = "events_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String eventId;

	@Column(name = "insurance_id", nullable = false)
	protected String insuranceId;

	@Column(name = "content", nullable = false)
	protected String content;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	public InsurancePersistenceEvent() {

	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
