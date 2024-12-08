package com.example.demo.modle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class MeetapimSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;
    private String meetupNmae;
    private String meetupDescription;

    //פרוט הארוע-המיטאפ
    private Date meetupDate;
    private String nameOfTheHostCompany;
    private String addressHostCompany;
    private String timeOfTheMeetup;
    private String WhoIsthemeetupfor;

    //הצגת הפוסטר של פרסום המיאפ
    private String poster_img_meetup;
    //לינק של WASE
    private String url_wase;

    //תלות עם JoiningMeetup

    @OneToMany(mappedBy = "meetapimSchedule")
    @JsonIgnore
    private List<JoiningMeetup> joiningMeetupList;


    //get&set

    public Date getMeetupDate() {
        return meetupDate;
    }

    public void setMeetupDate(Date meetupDate) {
        this.meetupDate = meetupDate;
    }

    public String getPoster_img_meetup() {
        return poster_img_meetup;
    }

    public void setPoster_img_meetup(String poster_img_meetup) {
        this.poster_img_meetup = poster_img_meetup;
    }

    public String getUrl_wase() {
        return url_wase;
    }

    public void setUrl_wase(String url_wase) {
        this.url_wase = url_wase;
    }

    public List<JoiningMeetup> getJoiningMeetupList() {
        return joiningMeetupList;
    }

    public void setJoiningMeetupList(List<JoiningMeetup> joiningMeetupList) {
        this.joiningMeetupList = joiningMeetupList;
    }

    public String getAddressHostCompany() {
        return addressHostCompany;
    }

    public void setAddressHostCompany(String addressHostCompany) {
        this.addressHostCompany = addressHostCompany;
    }

    public String getNameOfTheHostCompany() {
        return nameOfTheHostCompany;
    }

    public void setNameOfTheHostCompany(String nameOfTheHostCompany) {
        this.nameOfTheHostCompany = nameOfTheHostCompany;
    }

    public String getTimeOfTheMeetup() {
        return timeOfTheMeetup;
    }

    public void setTimeOfTheMeetup(String timeOfTheMeetup) {
        this.timeOfTheMeetup = timeOfTheMeetup;
    }

    public String getWhoIsthemeetupfor() {
        return WhoIsthemeetupfor;
    }

    public void setWhoIsthemeetupfor(String whoIsthemeetupfor) {
        WhoIsthemeetupfor = whoIsthemeetupfor;
    }

    public String getMeetupNmae() {
        return meetupNmae;
    }

    public void setMeetupNmae(String meetupNmae) {
        this.meetupNmae = meetupNmae;
    }

    public String getMeetupDescription() {
        return meetupDescription;
    }

    public void setMeetupDescription(String meetupDescription) {
        this.meetupDescription = meetupDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
