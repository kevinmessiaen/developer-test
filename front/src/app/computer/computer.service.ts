import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComputerService {

  constructor(private readonly httpClient: HttpClient) { }

  public uploadCommunications(file: File): Observable<number> {
    const formData = new FormData();
    formData.append('files', file);
    return this.httpClient.post<number>('http://localhost:8080/odds', formData);
  }
}
