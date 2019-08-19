package com.usl.microservices.sampleeventsourcing.service;

import com.usl.microservices.sampleeventsourcing.domain.Insurance;
import com.usl.microservices.sampleeventsourcing.dto.CancelInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.dto.CreateInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.dto.ModifyInsuranceRequest;

public interface InsuranceService {

	Insurance createInsurance(CreateInsuranceRequest createRequest);

	Insurance modifyInsurance(String insuranceId, ModifyInsuranceRequest mtaRequest);

	Insurance cancelInsurance(String insuranceId, CancelInsuranceRequest cancelRequest);

	Insurance getInsurance(String insuranceId);
}
