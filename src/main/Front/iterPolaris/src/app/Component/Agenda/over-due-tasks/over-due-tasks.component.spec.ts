import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverDueTasksComponent } from './over-due-tasks.component';

describe('OverDueTasksComponent', () => {
  let component: OverDueTasksComponent;
  let fixture: ComponentFixture<OverDueTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OverDueTasksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OverDueTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
