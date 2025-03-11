import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ZoneService } from '../../../services/zone.service';

@Component({
  selector: 'app-zone-projects',
  templateUrl: './zone-projects.component.html',
  styleUrl: './zone-projects.component.css'
})
export class ZoneProjectsComponent implements OnInit {
  projects: any[] = [];
  zoneId!: number;
  zoneService = inject(ZoneService);
  route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.zoneId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.zoneService.getZoneProjects(this.zoneId).subscribe((data) => {
      this.projects = data;
    });
  }
}
