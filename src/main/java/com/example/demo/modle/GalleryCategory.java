package com.example.demo.modle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class GalleryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameMeetup;
    private String descriptionMeetup;
    private String companyName;

    //תמונה של הלוגו של המיטאפ
    private String img_meetup;

    //תלות עם מטאפיים עצמם
//    @OneToMany(mappedBy = "gallery_category")
//    private List<Meetups> meetupsList;

    //תלות עם המצגות של המיטאפיים
//    @OneToMany(mappedBy = "galleryCategoryOf_presentation")
//    private  List<PresentationsOfMyths> presentationsOfMythsList;

    //תלות ען קבצי מיטאפ
    @OneToMany(mappedBy = "gallery_category")
    @JsonIgnore
    private  List<FileMeetup> fileMeetupList;


    //get&&set

//    public List<Meetups> getMeetupsList() {
//        return meetupsList;
//    }

//    public void setMeetupsList(List<Meetups> meetupsList) {
//        this.meetupsList = meetupsList;
//    }
//
//    public List<PresentationsOfMyths> getPresentationsOfMythsList() {
//        return presentationsOfMythsList;
//    }
//
//    public void setPresentationsOfMythsList(List<PresentationsOfMyths> presentationsOfMythsList) {
//        this.presentationsOfMythsList = presentationsOfMythsList;
//    }

    public String getImg_meetup() {
        return img_meetup;
    }

    public void setImg_meetup(String img_meetup) {
        this.img_meetup = img_meetup;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescriptionMeetup() {
        return descriptionMeetup;
    }

    public void setDescriptionMeetup(String descriptionMeetup) {
        this.descriptionMeetup = descriptionMeetup;
    }

    public String getNameMeetup() {
        return nameMeetup;
    }

    public void setNameMeetup(String nameMeetup) {
        this.nameMeetup = nameMeetup;
    }
}
