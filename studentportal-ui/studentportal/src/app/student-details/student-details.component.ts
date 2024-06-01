import { Component, OnInit } from '@angular/core';
import { StudentDataService } from '../service/student-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../POJOS/Student';


@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit{
  id!: number;
  studentDetails!: Student;
  deleteMessage!: string;
  courseLength!: number
  roleAdmin: boolean = false;
  unauthorisedMsg : boolean = false;

  constructor(private service: StudentDataService,
    private route: Router,
    private actRouter: ActivatedRoute) { }

    ngOnInit() {
      this.id = this.actRouter.snapshot.params['id'];
      this.refresh();
      console.log('id :' + this.id)
      // this.studentDetails.courses 
      // this.courseLength = this.studentDetails.courses.length;
      if(sessionStorage.getItem('role') == "ROLE_ADMIN"){
        this.roleAdmin = true;
      }
    }

    refresh() {
      this.service.getStudentById(this.id).subscribe(
        result => {
          console.log(result);
          this.studentDetails = result.data;
        },
        error => {
          console.log(error)
          this.unauthorisedMsg = true
          console.log(this.unauthorisedMsg)
        }
      )
    }

  updateStudent(id: number) {
    console.log(`update student with id : ${id}`)
    this.route.navigate(['student', id]);
  }

  deleteStudent(id: number) {
    console.log(`delete student with id : ${id}`);
    this.service.deleteStudent(id).subscribe(
      result => {
        console.log(result);
        this.deleteMessage = "Student has been deleted";
         this.reloadPage();
        // this.route.navigate([`student/${id}/courses`])
      },
      error => console.log(error)
      )
      // this.refresh();
    }

 return(){
  this.route.navigate(['students']);
 }
    
 reloadPage(){
    setTimeout(()=> {
      location.reload()
    }, 1000) // 1sec = 1000 milliseconds
 }

}
