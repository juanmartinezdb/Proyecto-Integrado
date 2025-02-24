import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JournalEntryDetailsComponent } from './journal-entry-details.component';

describe('JournalEntryDetailsComponent', () => {
  let component: JournalEntryDetailsComponent;
  let fixture: ComponentFixture<JournalEntryDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JournalEntryDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JournalEntryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
