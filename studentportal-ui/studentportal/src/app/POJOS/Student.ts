export interface Student {
  stdId: number
  stdName: string
  stdEmail: string
  courses: Course[]
}

export interface Course {
  courseId: number
  courseName: string
}
