package com.example.demo.dto;

import java.util.Date;

public class MeetupSchedleDTO {

    private Date meetupDate;
    private String nameOfTheHostCompany;
    private String addressHostCompany;
    private String timeOfTheMeetup;
    private String WhoIsthemeetupfor;

    //הצגת הפוסטר של פרסום המיאפ
    private String poster_img_meetup;

    //get&set
    public String getAddressHostCompany() {
        return addressHostCompany;
    }

    public void setAddressHostCompany(String addressHostCompany) {
        this.addressHostCompany = addressHostCompany;
    }

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
}
