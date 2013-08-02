package com.ksoft.fbase.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class Student
{
    private String name;
    private String roll_no;
    private String fathers_name;
    private String studying_in;
    
    private static ArrayList<Student> students = new ArrayList<Student>();

    public Student(String name,String roll_no ,String fathers_name, String studying_in) {
		
    	this.name = name;
    	this.roll_no = roll_no;
    	this.fathers_name = fathers_name;
    	this.studying_in = studying_in;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRoll_no() {
        return roll_no;
    }
    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }
    public String getFathers_name() {
        return fathers_name;
    }
    public void setFathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }
    public String getStudying_in() {
        return studying_in;
    }
    public void setStudying_in(String studying_in) {
        this.studying_in = studying_in;
    }
    
    public static ArrayList<Student> getStudentList()
    {
        return students;
    }
    
    public static void addStudent(Student student)
    {
        students.add(student);
    }

}