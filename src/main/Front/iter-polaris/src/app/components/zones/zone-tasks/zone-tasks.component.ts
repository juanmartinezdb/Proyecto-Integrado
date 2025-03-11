import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ZoneService } from '../../../services/zone.service';

@Component({
  selector: 'app-zone-tasks',
  templateUrl: './zone-tasks.component.html',
  styleUrl: './zone-tasks.component.css'
})
export class ZoneTasksComponent implements OnInit {
  tasks: any[] = [];
  zoneId!: number;
  zoneService = inject(ZoneService);
  route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.zoneId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.zoneService.getZoneTasks(this.zoneId).subscribe((data) => {
      this.tasks = data;
    });
  }
}
