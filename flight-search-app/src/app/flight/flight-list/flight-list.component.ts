import { Component, OnInit } from '@angular/core';
import { SerachApiService } from 'src/app/services/serach-api.service';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {

  flightList:any = [];
  flightNumber:string;
  origin:string;
  destination:string;
  departure:string;
  isError:boolean = false;
  constructor(private service:SerachApiService) { }

  ngOnInit() {
    this.service.getCompleteFlightList().subscribe((response)=>{
      this.flightList = response;
    },
  (error)=>{
    console.log(error);
  });
  }

  search(){
    if(!this.origin && !this.destination && !this.departure){
      this.isError=true;
    }
    else{
      this.isError=false;
      this.service.getFilteredFlightList(this.flightNumber,this.origin,this.destination,this.departure).subscribe((data)=>{
        this.flightList = data;
      }
    }
  }

}
