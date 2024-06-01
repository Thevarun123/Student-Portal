export interface Student {
    stdId: number
    stdName: string
    stdEmail: string
    courses: Course[]
    password: string
  }
  
  export interface Course {
    courseId: number
    courseName: string
  }
  