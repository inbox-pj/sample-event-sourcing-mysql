package com.usl.microservices.sampleeventsourcing.controller;

import com.usl.microservices.sampleeventsourcing.domain.Insurance;
import com.usl.microservices.sampleeventsourcing.dto.CancelInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.dto.CreateInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.dto.ModifyInsuranceRequest;
import com.usl.microservices.sampleeventsourcing.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/insurance", produces = "application/json")
public class InsuranceController {

	@Autowired
	private InsuranceService service;

	@GetMapping("/{insuranceId}")
	public Insurance getInsurance(@PathVariable String insuranceId) {
		return service.getInsurance(insuranceId);
	}

	@PostMapping("/create")
	public Insurance createInsurance(@RequestBody CreateInsuranceRequest Request) {
		return service.createInsurance(Request);
	}

	@PutMapping("/modify/{insuranceId}")
	public Insurance modifyInsurance(@PathVariable String insuranceId, @RequestBody ModifyInsuranceRequest Request) {
		return service.modifyInsurance(insuranceId, Request);
	}

	@PostMapping("/cancel/{insuranceId}")
	public Insurance cancelInsurance(@PathVariable String insuranceId, @RequestBody CancelInsuranceRequest Request) {
		return service.cancelInsurance(insuranceId, Request);
	}

}
