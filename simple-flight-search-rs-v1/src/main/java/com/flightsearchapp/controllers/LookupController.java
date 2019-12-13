package com.flightsearchapp.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightsearchapp.models.FlightDetail;
import com.flightsearchapp.services.LookupService;

@RestController
@RequestMapping(value="/v1/api")

/*
 * LookupController
 * This controller class provides REST endpoints in order to retrieve flight details
 */
public class LookupController {
	
	Logger logger = LogManager.getLogger(LookupController.class);//Logger instance
	
	@Autowired(required=true)
	private LookupService lookupService;//contains lookup logic
	
	/*
	 * Fetches all flights available in json file
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/flight-list", produces = { "application/json" })
	public List<FlightDetail> getAllFlights() {
		logger.info("LookupController : get all flights");
		return this.lookupService.getAll();
	}
	
	/*
	 * Fetches a country from a given continent
	 */
	@GetMapping("/flight-list/search")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<FlightDetail> getFlightListByParams(
			@RequestParam(required=false) String flightNumber,
			@RequestParam(required=true) String origin,
			@RequestParam(required=true) String destination,
			@RequestParam(required=true) String departure) throws Exception {	
		if(null == origin || "".equals(origin)) {
			throw new Exception("Invalid origin");
		}
		if(null == destination || "".equals(destination)) {
			throw new Exception("Invalid destination name");
		}
		if(null == departure || "".equals(departure)) {
			throw new Exception("Invalid departure");
		}
		logger.info("LookupController : get flight list by  filter params");
		return this.lookupService.getFlightListByParams(flightNumber,origin,destination,departure);
	}
}
