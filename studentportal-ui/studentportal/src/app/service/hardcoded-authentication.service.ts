import { HttpClient } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map } from 'rxjs';
import { json } from 'stream/consumers';

const url = 'http://localhost:8080';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'


@Injectable({
  providedIn: 'root'
})

export class HardcodedAuthenticationService {
  name: any;
  role: any;
  constructor(private http: HttpClient) { }

  // authenticate(username: string, password: string) {
  //   //console.log('before ' + this.isUserLoggedIn());d
  //   if (username === "in28minutes" && password === 'dummy') {
  //     window.sessionStorage.setItem('authenticaterUser', username);
  //     //console.log('after ' + this.isUserLoggedIn());
  //     return true;
  //   }
  //   return false;
  // }

  executeJWTAuthenticationService(loginRequest: any) {

    return this.http.post<any>(
      `${url}/login`, loginRequest).pipe(
        map(
          data => {
            console.log("from api : " + JSON.stringify(data));
            sessionStorage.setItem(AUTHENTICATED_USER, loginRequest.email);
            sessionStorage.setItem(TOKEN, `${data[0].jwt}`);
            this.setRole();
            return data;
          }
        )
      );
    //console.log("Execute Hello World Bean Service")
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN)
    return null
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout() {
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(TOKEN)
    sessionStorage.removeItem('role')
    
  }


  setRole() {
    const helper = new JwtHelperService();
    const decodeToken = helper.decodeToken(
      sessionStorage.getItem('token') || ''
    );

    this.name = decodeToken?.sub;
    this.role = decodeToken['role'];

    sessionStorage.setItem('role', this.role);
  }

  

}