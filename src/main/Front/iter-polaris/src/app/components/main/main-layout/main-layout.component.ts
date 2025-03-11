import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { TopNavComponent } from '../top-nav/top-nav.component';
import { SideBarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  imports: [TopNavComponent, SideBarComponent, RouterOutlet],
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent {}
