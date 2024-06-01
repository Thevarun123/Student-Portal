import { Component, OnInit } from '@angular/core';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
    roleAdmin: boolean = false
    constructor(public service : HardcodedAuthenticationService){}

    ngOnInit(){
      if(sessionStorage.getItem('role') == "ROLE_ADMIN"){
           this.roleAdmin = true;
      }
    }
    
  }
