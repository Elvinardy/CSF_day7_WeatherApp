import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CitylistComponent } from './component/citylist.component';
import { WeatherComponent } from './component/weather.component';
import { WeatherService } from './weather.service';

const appRoutes: Routes = [
  { path: '', component: CitylistComponent},
  { path: 'weather/:city', component: WeatherComponent},
  { path: "**", redirectTo:'/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    CitylistComponent, WeatherComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,  // important for routings
    RouterModule.forRoot(appRoutes, {useHash: true}),
    HttpClientModule
  ],
  providers: [WeatherService],
  bootstrap: [AppComponent]
})
export class AppModule { }
