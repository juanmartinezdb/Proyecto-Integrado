import { Component } from '@angular/core';
import { HabitService } from '../../../services/habit.service';
import { HabitResponse } from '../../../models/habit.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-habit-list',
  imports: [RouterLink],
  templateUrl: './habit-list.component.html',
  styleUrl: './habit-list.component.css'
})
export class HabitListComponent {
  habits: HabitResponse[] = [];

  constructor(private habitService: HabitService) {}

  ngOnInit() {
    this.habitService.getHabits().subscribe((data) => {
      this.habits = data;
    });
  }
}
