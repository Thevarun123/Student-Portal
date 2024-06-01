import { Component, OnInit } from '@angular/core';
import { StudentDataService } from '../service/student-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../POJOS/Student';


export class StudentsDemo{
  constructor(public stdId: number, 
              public stdName: string,
              public stdEmail: string,
              public courses: []){}
}

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit {

  id!: number;
  studentDetails!: Student[];
  deleteMessage!: string;
  userErrorMsg!: string 
  student!: Student
  roleAdmin: boolean = false



  constructor(private service: StudentDataService,
    private route: Router,
    private actRouter: ActivatedRoute,) { }

  ngOnInit() {
    if(sessionStorage.getItem('role') == "ROLE_ADMIN"){
      this.roleAdmin = true;
    }
     this.refresh();  
  }

  refresh() {

    this.service.getStudents().subscribe(
      result => {
        console.log(result);
        console.log("if array " + Array.isArray(result.data));
        if(Array.isArray(result.data)){
          this.studentDetails = result.data;
        }else{
          this.student = result.data
        }
      },
      error => {
        console.log(error)
        // this.userErrorMsg = "You are not allowed to view other students details.";
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
        this.refresh();
      },
      error => console.log(error)
    )
  }

  addStudent(){
    console.log("add student is called")
    this.route.navigate(['/student'])
  }

  showStudentDetails(id : number){
      console.log("redirect to student details with id: " + id);
      this.route.navigate([`student/${id}/courses`]);
  }

}
