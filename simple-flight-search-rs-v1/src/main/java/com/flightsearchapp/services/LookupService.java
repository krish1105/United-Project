package com.flightsearchapp.services;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearchapp.models.FlightDetail;

/*
 * Lookup service class which computes information from json file
 */
@Service
public class LookupService {

	Logger logger = LogManager.getLogger(LookupService.class);//logger instance
	
	private List<FlightDetail> flightList = null;
	/*
	 * init method that reads json file and populates flight list
	 */
	 @PostConstruct
	  public void init(){
		 logger.info("LookupService : init - loading json data");
		 ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<FlightDetail>> mapType = new TypeReference<List<FlightDetail>>() {};
			InputStream is = TypeReference.class.getResourceAsStream("/json/flight-sample.json");
			
				try {
					this.flightList = mapper.readValue(is, mapType);
					
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	 
	 public List<FlightDetail> getAll(){
		 return this.flightList;
	 }
	
	 /*
		 * Fetches flight list from given filter params
		 */
		public List<FlightDetail> getFlightListByParams(String flightNumber,
				String origin,
				String destination,
				String departure) throws ParseException {
			
				// TODO Auto-generated method stub
			
			List<FlightDetail> list=new ArrayList<>();	


			for(FlightDetail detail:this.flightList) {
				//Date current = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(detail.getDeparture().toLocaleString());
				//Date depat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(departure);
				
		           
				if(!flightNumber.equals("")) {
				if( detail.getFlightNumber().equals(flightNumber) && detail.getDestination().equals(destination)
							&& detail.getOrigin().equals(origin)
							
							) {
					list.add(detail);
				}
				}else {
					if(detail.getDestination().equals(destination)
							&& detail.getOrigin().equals(origin)
							
							) {
					list.add(detail);
				}
				}
			}
			return list;
		}
	 
}
