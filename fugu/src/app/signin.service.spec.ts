import { TestBed, inject } from '@angular/core/testing';

import { SigninService } from './signin.service';

describe('SigninService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SigninService]
    });
  });

  it('should ...', inject([SigninService], (service: SigninService) => {
    expect(service).toBeTruthy();
  }));
});
