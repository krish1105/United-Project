import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class SerachApiService {

  constructor(private http:HttpClient) { }

  getCompleteFlightList(){
    return this.http.get("http://localhost:8080/v1/api/flight-list");
  }

  getFilteredFlightList(flightNumber,origin,destination,departure){
    return this.http.get("http://localhost:8080/v1/api/flight-list/search?flightNumber="+flightNumber+'&origin='+origin+'&destination='+destination+'&departure='+departure);
  }
}
