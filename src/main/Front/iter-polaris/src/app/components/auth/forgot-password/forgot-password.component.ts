import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-forgot-password',
  imports: [ReactiveFormsModule],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  authService = inject(AuthService);
  successMessage: string = '';

  ngOnInit(): void {
    this.formu = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.authService.forgotPassword(this.formu.value.email).subscribe(() => {
        this.successMessage = 'Si tu correo está registrado, recibirás un enlace para restablecer tu contraseña.';
      });
    } else {
      alert('Introduce un correo válido');
    }
  }
}
