import { TestBed, inject } from '@angular/core/testing';

import { SerachApiService } from './serach-api.service';

describe('SerachApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SerachApiService]
    });
  });

  it('should be created', inject([SerachApiService], (service: SerachApiService) => {
    expect(service).toBeTruthy();
  }));
});
