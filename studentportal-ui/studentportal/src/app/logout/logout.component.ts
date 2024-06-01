import { Component, OnInit } from '@angular/core';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})

export class LogoutComponent implements OnInit {

  logoutMsg: string = ''

   constructor(private service: HardcodedAuthenticationService,
               private router: Router){}

  ngOnInit() {
    this.service.logout();
    this.logoutMsg =  "You are succesfully signed out."
    this.reload();

  }

  reload(){
    setTimeout(()=>{
      location.reload(),3000
    })
  }

}
