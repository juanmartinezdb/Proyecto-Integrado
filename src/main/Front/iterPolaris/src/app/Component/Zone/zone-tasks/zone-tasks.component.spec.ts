import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneTasksComponent } from './zone-tasks.component';

describe('ZoneTasksComponent', () => {
  let component: ZoneTasksComponent;
  let fixture: ComponentFixture<ZoneTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZoneTasksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
