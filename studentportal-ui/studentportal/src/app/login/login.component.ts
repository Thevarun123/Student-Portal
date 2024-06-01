import { Component, OnInit } from '@angular/core';
// import { AuthenticateServiceService } from '../service/authenticate-service.service';
import { Router } from '@angular/router';
import { JwtServiceService } from '../service/jwt-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { error } from 'console';
import { StudentDataService } from '../service/student-data.service';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  email!: string;
  name: any;
  role: any;

  constructor(
    private authenticateService: HardcodedAuthenticationService,
    private dataService: StudentDataService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
    })
  }

  submitForm() {
    console.log("email "+ this.loginForm.value.email);
    this.email = this.loginForm.value.email;

    
    this.authenticateService.executeJWTAuthenticationService(this.loginForm.value).subscribe(
      (response) => {

        console.log(response[0].jwt);

        // if (response[0].jwt != null) {
          // const jwtToken = response[0].jwt;
          // localStorage.setItem('jwt', jwtToken);
          // this.fetchStudentByEmail(this.email);
          this.router.navigate(['students'])
          
        // }
      },error => console.log(error)
    )
  }

  fetchStudentByEmail(email : string){
     this.dataService.fetchStudentByEmailId(email).subscribe(
       result => {
            console.log(result);
            let id  = result.data.stdId;
            this.router.navigate([`student/${id}/courses`])
       }
     )
  }
// password is their names only

 

}