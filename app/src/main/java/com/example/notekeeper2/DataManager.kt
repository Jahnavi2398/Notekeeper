package com.example.notekeeper2

import kotlin.jvm.internal.Intrinsics

object DataManager {
    val courses = HashMap<String , CourseInfo>()

    val notes = ArrayList<NotesInfo>()
    // storing type NotesInfo in ArrayList(This ArrayList is generic type)

    //Intializer block

    init {
        IntializeCourses()
        IntializeNotes()
    }
    // Function declaration....after this Intializer Block
    //Since we don't want anyone from outside to call this method
    // therefore,declare it as private
    private fun IntializeCourses(){
        var course = CourseInfo("CS001" , "Kotlin")
        courses.set(course.courseId , course)

        course = CourseInfo("CS002" ,"Java")
        courses.set(course.courseId , course)

        course = CourseInfo("CS003" , "Python")
        courses.set(course.courseId ,course)

        course = CourseInfo("CS004" , "Ruby")
        courses.set(course.courseId , course)

    }

    private fun IntializeNotes(){
        var notess = NotesInfo(
            CourseInfo("CS001" , "Kotlin"),"Dynamics resolution",
        "Kotlin is android language")
        notes.add(notess)
        notess = NotesInfo(CourseInfo("CS002" , "Java"), "Dynamics resolution-2",
            "Java is fast android language")
        notes.add(notess)
        notess = NotesInfo(CourseInfo("CS003" , "Python"), "Dynamics resolution-3",
            "Python is quite an easy language")
        notes.add(notess)
        notess = NotesInfo(CourseInfo("CS004" , "Ruby"), "Dynamics resolution-4",
            "Ruby is new and famous language")
        notes.add(notess)


    }


}