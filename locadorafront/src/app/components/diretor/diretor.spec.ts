import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Diretor } from './diretor';

describe('Diretor', () => {
  let component: Diretor;
  let fixture: ComponentFixture<Diretor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Diretor]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Diretor);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
