package com.edutrack.model;

public class Student {
    private int id;
    private String name;
    private String rollNo;
    private String studentClass;
    private String section;
    private java.sql.Date dob;
    private String email;
    private String photo;

    public Student() {}

    public Student(int id, String name, String rollNo, String studentClass, String section, java.sql.Date dob, String email, String photo) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.studentClass = studentClass;
        this.section = section;
        this.dob = dob;
        this.email = email;
        this.photo = photo;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public String getStudentClass() { return studentClass; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public java.sql.Date getDob() { return dob; }
    public void setDob(java.sql.Date dob) { this.dob = dob; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}
