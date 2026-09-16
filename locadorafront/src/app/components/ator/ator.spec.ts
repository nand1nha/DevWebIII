import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ator } from './ator';

describe('Ator', () => {
  let component: Ator;
  let fixture: ComponentFixture<Ator>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Ator]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Ator);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
