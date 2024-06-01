import { Component, OnInit } from '@angular/core';
import { StudentDataService } from '../service/student-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Course, Student } from '../POJOS/Student';
import { StudentsDemo } from '../students/students.component';
import { CourseDetails } from '../POJOS/CourseDetails';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';


@Component({
  selector: 'app-upgrade-students',
  templateUrl: './upgrade-students.component.html',
  styleUrl: './upgrade-students.component.css'
})
export class UpgradeStudentsComponent implements OnInit {

  id!: number;
  std!: Student;
  course!: Course;
  courseDetails!: CourseDetails[]
  courseUpdated!: CourseDetails
  coursesUpdatedArray!: CourseDetails[]
  dropdownSettings: IDropdownSettings = {}
  new!: CourseDetails[]
  isEmailChanges: boolean = false

  constructor(private service: StudentDataService,
    private authService: HardcodedAuthenticationService,
    private route: Router,
    private actRouter: ActivatedRoute) { }

  ngOnInit() {
    
    this.id = this.actRouter.snapshot.params['id'];

    this.std = new StudentsDemo(this.id, '', '', []);


    this.service.getStudentById(this.id).subscribe(
      result => {
        console.log("from ngoninit" + result)
        this.std = result.data;
      }
    )


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
      allowSearchFilter: true,
      searchPlaceholderText: "search your course"
    };
  }


  onSubmit() {
    // console.log(this.new); 
    // this.std.courses = this.coursesUpdatedArray;
    console.log(this.isEmailChanges)

    console.log(this.std.courses)
    this.service.updateStudent(this.id, this.std).subscribe(
      result => {
        console.log(result);
        this.route.navigate([`student/${this.id}/courses`])
      }
    )
  }


  // onItemSelect(course: any){
  //    console.log("selected course: "+ course);
  //   //  this.courseUpdated.concat(course);
  //   // this.courseUpdated = course;
  //    console.log(course)

  //   // let len = this.std.courses.push(this.courseUpdated)
  //    console.log(this.std.courses);  //4 //5 //6

  //   // this.new = this.coursesUpdatedArray.concat(this.std.courses);
  //   //  console.log(this.coursesUpdatedArray)

  //   //  let len2 = this.coursesUpdatedArray.push(course)
  //   //  console.log(len2);
  // }

}


