import { Component } from '@angular/core';
import { EnergyBalanceComponent } from './energy-balance/energy-balance.component';

@Component({
  selector: 'app-top-nav',
  imports: [EnergyBalanceComponent],
  templateUrl: './top-nav.component.html',
  styleUrl: './top-nav.component.css'
})
export class TopNavComponent {

}
