import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Weather } from '../model';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  weather!: Weather;
  city = '';
  fields = '';
  constructor(private activedRoute: ActivatedRoute, private weatherSvc: WeatherService) { }

  ngOnInit(): void {
   this.city = this.activedRoute.snapshot.params['city'];
   this.fields = this.activedRoute.snapshot.params['fields'];
   console.info('fields = ', this.fields);

   this.weatherSvc.getWeather(this.city)
    .then(w => this.weather = w)
  }

}
