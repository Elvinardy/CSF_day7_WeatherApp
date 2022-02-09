import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-citylist',
  templateUrl: './citylist.component.html',
  styleUrls: ['./citylist.component.css']
})
export class CitylistComponent implements OnInit {

  cities = ['Singapore', 'Tokyo', 'Paris',
  'London', 'New York','Taipei', 'Kuala Lumpur','Bangkok'];

  constructor(private router: Router, private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  go(city: string) {
    const params = {
      fields: 'imperial',
    }
    this.router.navigate(['/weather',city],
    { queryParams: params })
  }

}
