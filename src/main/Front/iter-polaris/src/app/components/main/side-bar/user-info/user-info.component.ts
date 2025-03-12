import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../../../../services/user.service';
import { UserDTO } from '../../../../models/user.model';

@Component({
  selector: 'app-user-info',
  imports: [],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent implements OnInit {
  user: UserDTO = { id: 0, username: '', email: '', role: '' }; // Se inicializa correctamente
  userService = inject(UserService);

  ngOnInit(): void {
    this.userService.getUserProfile().subscribe((data) => {
      this.user = data!;
    });
  }
}
