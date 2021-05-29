package models;

import java.util.Date;

public class Student {

    private String fullname, email, program, department, gender, mobile_number, student_type, address, fname, mname, lname, section;
    private Date dateofbirth;
    private int id, year_level;

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getProgram() {
        return program;
    }

    public String getDepartment() {
        return department;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getStudent_type() {
        return student_type;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getSection() {
        return section;
    }

    public Student(String section, String fullname, String fname, String mname, String lname, String email, String program, String department, String gender, String mobile_number, String student_type, String address, Date dateofbirth, int id, int year_level) {
        this.fullname = fullname;
        this.section = section;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.program = program;
        this.department = department;
        this.gender = gender;
        this.mobile_number = mobile_number;
        this.student_type = student_type;
        this.address = address;
        this.dateofbirth = dateofbirth;
        this.id = id;
        this.year_level = year_level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", program='" + program + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", student_type='" + student_type + '\'' +
                ", address='" + address + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", lname='" + lname + '\'' +
                ", section='" + section + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", id=" + id +
                ", year_level=" + year_level +
                '}';
    }

    public int getYear_level() {
        return year_level;
    }

}