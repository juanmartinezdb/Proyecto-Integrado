import { Component } from '@angular/core';
import { StatsService } from '../../../services/stats.service';
import { UserStatsResponse } from '../../../models/stats.model';

@Component({
  selector: 'app-stats',
  imports: [],
  templateUrl: './stats.component.html',
  styleUrl: './stats.component.css'
})
export class StatsComponent {
  stats!: UserStatsResponse;

  constructor(private statsService: StatsService) {}

  ngOnInit() {
    this.statsService.getUserStats().subscribe((data) => {
      this.stats = data;
    });
  }
}
