import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ZoneService } from '../../../services/zone.service';

@Component({
  selector: 'app-zone-habits',
  templateUrl: './zone-habits.component.html',
  styleUrl: './zone-habits.component.css'
})
export class ZoneHabitsComponent implements OnInit {
  habits: any[] = [];
  zoneId!: number;
  zoneService = inject(ZoneService);
  route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.zoneId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.zoneService.getZoneHabits(this.zoneId).subscribe((data) => {
      this.habits = data;
    });
  }
}
