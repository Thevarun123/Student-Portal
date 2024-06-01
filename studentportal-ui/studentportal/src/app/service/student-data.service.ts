import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservableLike } from 'rxjs';
import { Student } from '../POJOS/Student';

@Injectable({
  providedIn: 'root'
})
export class StudentDataService {

  private url = 'http://localhost:8080/students';

  private coursesUrl = 'http://localhost:8080/course/details';

  header!: null;

  constructor(private http: HttpClient) { }


  //GET
  getStudents(): Observable<any> {
    return this.http.get(`${this.url}`, {
      headers: this.createAuhtorizationHeader()
    });
  }

  // GET By Id (id)
  getStudentById(id: number): Observable<any> {
    return this.http.get<Student>(`${this.url}/${id}`, {
      headers: this.createAuhtorizationHeader()
    });
  }

  //POST, (body)
  createStudent(student: Student): Observable<object> {
    return this.http.post(`${this.url}`, student, {
      headers: this.createAuhtorizationHeader()
    });
  }

  //Put (id, body)
  updateStudent(id: number, student: Student): Observable<object> {
    return this.http.put(`${this.url}/${id}`, student, {
      headers: this.createAuhtorizationHeader()
    });
  }

  //DELETE, (id)
  deleteStudent(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`, {
      headers: this.createAuhtorizationHeader()
    });
  }

  getCourses(): Observable<any> {
    return this.http.get(`${this.coursesUrl}`, {
      headers: this.createAuhtorizationHeader()
    })
  }

  getCoursesById(id: number): Observable<any> {
    return this.http.get(`${this.coursesUrl}/${id}`, {
      headers: this.createAuhtorizationHeader()
    })
  }

  private createAuhtorizationHeader() {
    const jwtToken = window.sessionStorage.getItem('token');
    if (jwtToken) {
      console.log("JWT token found in session storage", jwtToken);
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      )
    } else {
      console.log("JWT token not found in local storage");
    }
    return undefined;
  }
   
   public fetchStudentByEmailId(email : string): Observable<any>{
      return this.http.get(`${this.url}/email/${email}`, {
        headers: this.createAuhtorizationHeader()
      })
   }
}
