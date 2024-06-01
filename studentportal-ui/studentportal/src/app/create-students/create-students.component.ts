import { Component, OnInit } from '@angular/core';
import { StudentsDemo } from '../students/students.component';
import { } from '../POJOS/Student';
import { StudentDataService } from '../service/student-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { json } from 'express';
import { CourseDetails } from '../POJOS/CourseDetails';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { Course, Student } from '../POJOS/StudentWithPassword';

export class studentWithPassword{
  constructor(public stdId: number,
              public stdName: string,
              public stdEmail: string,
              public courses: [],
              public password: string){}
}

@Component({
  selector: 'app-create-students',
  templateUrl: './create-students.component.html',
  styleUrl: './create-students.component.css'
})
export class CreateStudentsComponent implements OnInit {

  std!: Student;
  courseStr: string = "";
  course!: Course[];
  courseId!: number;
  courseName!: string;
  dropdownSettings: IDropdownSettings = {};
  courseDetails!: CourseDetails[];
  disabled!: boolean;

  constructor(private service: StudentDataService,
    private route: Router,
    private actRouter: ActivatedRoute) { }

  ngOnInit() {
    // this.std = new StudentsDemo(9, '', '', []);  
    this.std = new studentWithPassword(9, '', '', [], '');  
  

    // injecting values from backend to courseDetails, whenever the create student is called, values in the courses dropdown will be populated
     this.service.getCourses().subscribe(
      result => {
           console.log("courses from backend: " + result);
           this.courseDetails = result;
      }
     )

    this.dropdownSettings = {
      idField: 'courseId',
      textField: 'courseName',
      selectAllText: "Select All Items From List",
      unSelectAllText: "UnSelect All Items From List",
      allowSearchFilter: true
    };

  }


  onSubmit() {

    // this.std.courses = this.course;
    console.log("course: " + this.std.courses)

    this.service.createStudent(this.std).subscribe(
      result => {
        console.log("Result :" + result);
        this.route.navigate(['students']);
      }
    )
  }

  idSubmit(id: number) {
    this.courseId = id;
  }

}
