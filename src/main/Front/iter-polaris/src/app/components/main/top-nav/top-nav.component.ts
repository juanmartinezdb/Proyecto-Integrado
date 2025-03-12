import { Component, OnInit, inject } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { RouterLink } from '@angular/router';
import { EnergyBalanceComponent } from './energy-balance/energy-balance.component';

@Component({
  selector: 'app-top-nav',
  imports: [RouterLink,EnergyBalanceComponent],
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  userName = 'Cargando...';
  userService = inject(UserService);

  ngOnInit(): void {
    this.userService.getUserProfile().subscribe(user => {
      this.userName = user ? user.username : 'Usuario desconocido';
    });

    this.userService.fetchUserProfile();
  }
}
