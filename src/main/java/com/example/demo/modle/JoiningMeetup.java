package com.example.demo.modle;

import jakarta.persistence.*;

@Entity
public class JoiningMeetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String familyName;
    private String phoneNumber;
    private String role;
    private String email;

    //MeetapimSchedule תלות עם
    @ManyToOne
    @JoinColumn(name = "meetapimSchedule_id", nullable = false)
    private MeetapimSchedule meetapimSchedule;

    //get&&set

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MeetapimSchedule getMeetapimSchedule() {
        return meetapimSchedule;
    }

    public void setMeetapimSchedule(MeetapimSchedule meetapimSchedule) {
        this.meetapimSchedule = meetapimSchedule;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
