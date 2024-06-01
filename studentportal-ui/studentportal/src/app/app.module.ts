import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgIf } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { StudentsComponent } from './students/students.component';
import { UpgradeStudentsComponent } from './upgrade-students/upgrade-students.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ErrorComponent } from './error/error.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateStudentsComponent } from './create-students/create-students.component';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    UpgradeStudentsComponent,
    LoginComponent,
    LogoutComponent,
    ErrorComponent,
    HeaderComponent,
    FooterComponent,
    CreateStudentsComponent,
    StudentDetailsComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgIf,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot()
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
