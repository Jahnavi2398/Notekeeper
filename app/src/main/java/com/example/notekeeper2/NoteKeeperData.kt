package com.example.notekeeper2

data class CourseInfo(val courseId:String , val title:String) {
    override fun toString(): String {
        return title
    }
}
data class NotesInfo(var course:CourseInfo? = null , var title:String?=null , var text : String?=null)