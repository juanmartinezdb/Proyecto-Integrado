import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneProjectsComponent } from './zone-projects.component';

describe('ZoneProjectsComponent', () => {
  let component: ZoneProjectsComponent;
  let fixture: ComponentFixture<ZoneProjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZoneProjectsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
