import { Component } from '@angular/core';
import { AchievementResponse } from '../../../models/gamification.model';
import { AchievementService } from '../../../services/achievement.service';

@Component({
  selector: 'app-achievements',
  imports: [],
  templateUrl: './achievements.component.html',
  styleUrl: './achievements.component.css'
})
export class AchievementsComponent {
  achievements: AchievementResponse[] = [];

  constructor(private achievementService: AchievementService) {}

  ngOnInit() {
    this.achievementService.getAchievements().subscribe((data) => {
      this.achievements = data;
    });
  }
}
