import { Component } from '@angular/core';
import { GamificationService } from '../../../services/gamification.service';
import { SkillResponse } from '../../../models/gamification.model';

@Component({
  selector: 'app-skills-list',
  imports: [],
  templateUrl: './skills-list.component.html',
  styleUrl: './skills-list.component.css'
})
export class SkillsListComponent {
  skills: SkillResponse[] = [];

  constructor(private gamificationService: GamificationService) {}

  ngOnInit() {
    this.gamificationService.getSkills().subscribe((data) => {
      this.skills = data;
    });
  }
}
