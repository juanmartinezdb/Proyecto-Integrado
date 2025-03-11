import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-energy-balance',
  imports: [],
  templateUrl: './energy-balance.component.html',
  styleUrl: './energy-balance.component.css'
})
export class EnergyBalanceComponent implements OnInit {
  energy: number = 0;
  maxEnergy: number = 100;
  userService = inject(UserService);

  ngOnInit(): void {
    this.userService.getEnergyBalance().subscribe((data: { energy: number; maxEnergy: number }) => {
      this.energy = data.energy;
      this.maxEnergy = data.maxEnergy;
    });
  }
}
