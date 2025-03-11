import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-calendar-settings',
  imports: [ReactiveFormsModule],
  templateUrl: './calendar-settings.component.html',
  styleUrl: './calendar-settings.component.css'
})
export class CalendarSettingsComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  userService = inject(UserService);

  ngOnInit(): void {
    this.formu = this.fb.group({
      startOfWeek: ['Monday'],
      timeFormat: ['24h']
    });

    this.userService.getCalendarSettings().subscribe(settings => {
      this.formu.patchValue(settings);
    });
  }

  submit() {
    this.userService.updateCalendarSettings(this.formu.value).subscribe(() => {
      alert('Configuraciones de calendario actualizadas');
    });
  }
}
