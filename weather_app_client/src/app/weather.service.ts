import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Weather } from "./model";


@Injectable()
export class WeatherService {

  constructor(private http: HttpClient) { }
  // using observables
  /* getWeather(city: string): Observable<any> {
    return this.http.get(`localhost:8080/api/weather/${city}`);
  } */

  // using Promise
  getWeather(city: string): Promise<Weather> {
    return lastValueFrom(
      this.http.get<Weather>(`/api/weather/${city}`)
    )
  }
}
