import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchWebComponent } from './search-web.component';

describe('SearchWebComponent', () => {
  let component: SearchWebComponent;
  let fixture: ComponentFixture<SearchWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
