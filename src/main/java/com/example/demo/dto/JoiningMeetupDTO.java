package com.example.demo.dto;

public class JoiningMeetupDTO {
    private Long id;
    private String name;
    private String familyName;
    private String phoneNumber;
    private String role;
    private String email;
    private Long meetapimSchedule_id;

    //get&&set

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeetapimSchedule_id() {
        return meetapimSchedule_id;
    }

    public void setMeetapimSchedule_id(Long meetapimSchedule_id) {
        this.meetapimSchedule_id = meetapimSchedule_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
