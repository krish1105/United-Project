import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FlightListComponent } from './flight/flight-list/flight-list.component';
import { SerachApiService } from 'src/app/services/serach-api.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 

@NgModule({
  declarations: [
    AppComponent,
    FlightListComponent,
    
    
    ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [SerachApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
