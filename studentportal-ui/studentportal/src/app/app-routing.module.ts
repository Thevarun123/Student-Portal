import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import path from 'path';
import { StudentsComponent } from './students/students.component';
import { UpgradeStudentsComponent } from './upgrade-students/upgrade-students.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ErrorComponent } from './error/error.component';
import { CreateStudentsComponent } from './create-students/create-students.component';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { RegisterComponent } from './register/register.component';
import { RouteGuardService } from './service/route-guard.service';

const routes: Routes = [
  { path: '', component: LoginComponent, canActivate: [RouteGuardService] },
  { path: 'students', component: StudentsComponent, canActivate: [RouteGuardService] },  //success
  { path: 'student/:id', component: UpgradeStudentsComponent, canActivate: [RouteGuardService] }, 
  { path: 'student/:id/courses', component: StudentDetailsComponent, canActivate: [RouteGuardService] }, //success
  { path: 'student', component: CreateStudentsComponent, canActivate: [RouteGuardService] },//success
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService] }, //success
  { path: "register", component: RegisterComponent },//success
  { path: '**-**', component: LoginComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
