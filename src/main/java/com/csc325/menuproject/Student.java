package com.csc325.menuproject;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String department;
    private String major;
    private String email;
    private String imageUrl;

    public Student(String id, String firstName, String lastName, String department, String major, String email, String imageUrl){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.major = major;
        this.email = email;
        this.imageUrl = imageUrl;
    }
    //Getters
    public String getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getDepartment(){
        return department;
    }
    public String getMajor(){
        return major;
    }
    public String getEmail(){
        return email;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    //Setters
    public void setId(String id) {
        this.id = id; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
