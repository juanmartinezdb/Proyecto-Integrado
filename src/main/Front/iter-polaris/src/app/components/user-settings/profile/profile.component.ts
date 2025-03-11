import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-profile',
  imports: [ReactiveFormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  userService = inject(UserService);
  userId: number = 0;

  ngOnInit(): void {
    this.formu = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]]
    });

    this.userService.getUserProfile().subscribe(user => {
      this.userId = user.id;
      this.formu.patchValue({
        username: user.username,
        email: user.email
      });
    });
  }

  submit() {
    if (this.formu.valid) {
      this.userService.updateUser(this.userId, this.formu.value).subscribe(() => {
        alert('Perfil actualizado correctamente');
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
