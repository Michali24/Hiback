package com.example.demo.dto;

public class GalleryCategoryDTO {
    private Long id;
    private String nameMeetup;
    private String descriptionMeetup;
    private String companyName;

    //תמונה של הלוגו של המיטאפ
    private String img_meetup;

    //get&set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImg_meetup() {
        return img_meetup;
    }

    public void setImg_meetup(String img_meetup) {
        this.img_meetup = img_meetup;
    }

    public String getNameMeetup() {
        return nameMeetup;
    }

    public void setNameMeetup(String nameMeetup) {
        this.nameMeetup = nameMeetup;
    }
}
