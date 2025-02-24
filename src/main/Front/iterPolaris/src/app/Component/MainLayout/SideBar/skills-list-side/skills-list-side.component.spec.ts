import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsListSideComponent } from './skills-list-side.component';

describe('SkillsListSideComponent', () => {
  let component: SkillsListSideComponent;
  let fixture: ComponentFixture<SkillsListSideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SkillsListSideComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkillsListSideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
