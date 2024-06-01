import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const url = 'http://localhost:8080';
@Injectable({
  providedIn: 'root'
})
export class JwtServiceService {

  constructor(private http: HttpClient) { }

  public register(signupRequest: any) : Observable<any>{
    return this.http.post(`${url}/signup`, signupRequest);
  }

  public login(loginRequest: any): Observable<any>{
    return this.http.post(`${url}/login`, loginRequest)
  }
}
