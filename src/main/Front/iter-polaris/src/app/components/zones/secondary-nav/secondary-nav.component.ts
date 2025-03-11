import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-secondary-nav',
  imports: [RouterModule],
  templateUrl: './secondary-nav.component.html',
  styleUrl: './secondary-nav.component.css'
})
export class SecondaryNavComponent {
  @Input() zoneId!: number; 
}
