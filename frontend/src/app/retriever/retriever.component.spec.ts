import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrieverComponent } from './retriever.component';

describe('RetrieverComponent', () => {
  let component: RetrieverComponent;
  let fixture: ComponentFixture<RetrieverComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetrieverComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetrieverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
