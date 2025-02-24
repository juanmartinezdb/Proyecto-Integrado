import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneHabitsComponent } from './zone-habits.component';

describe('ZoneHabitsComponent', () => {
  let component: ZoneHabitsComponent;
  let fixture: ComponentFixture<ZoneHabitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZoneHabitsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneHabitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
