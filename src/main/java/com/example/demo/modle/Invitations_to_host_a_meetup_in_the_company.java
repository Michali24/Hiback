package com.example.demo.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invitations_to_host_a_meetup_in_the_company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;
    private String description;
    private String company_name;
    private String company_adress;
    private String contact_name;
    private String phone_number_contact_name;
    private String email_contact_name;
    private int number_of_max_participants;
    //האם יש מקרן
    private boolean There_is_a_projector;
   //האם יש ציוד הגברה
    private boolean There_is_a_amplification_equipment;
    //האם יש מצלמת וידיאו
    private boolean there_is_a_vidio_camera;
    //האם יש סידור חנייה
    private boolean there_is_a_Parking_arrangement;

    //דברים שאולי נוסיף ואולי לא
    //האם התקבל אישור של הנהלת החברה לאירוח מיטאפ?
    //צרוץ תמונות של המקום
    //תאריכים אפשריים לאירוח
    //האם יש תקציב לכיבוד קל


//get&&set
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCompany_adress() {
        return company_adress;
    }

    public void setCompany_adress(String company_adress) {
        this.company_adress = company_adress;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail_contact_name() {
        return email_contact_name;
    }

    public void setEmail_contact_name(String email_contact_name) {
        this.email_contact_name = email_contact_name;
    }

    public int getNumber_of_max_participants() {
        return number_of_max_participants;
    }

    public void setNumber_of_max_participants(int number_of_participants) {
        this.number_of_max_participants = number_of_participants;
    }

    public String getPhone_number_contact_name() {
        return phone_number_contact_name;
    }

    public void setPhone_number_contact_name(String phone_number_contact_name) {
        this.phone_number_contact_name = phone_number_contact_name;
    }

    public boolean isThere_is_a_amplification_equipment() {
        return There_is_a_amplification_equipment;
    }

    public void setThere_is_a_amplification_equipment(boolean there_is_a_amplification_equipment) {
        There_is_a_amplification_equipment = there_is_a_amplification_equipment;
    }

    public boolean isThere_is_a_Parking_arrangement() {
        return there_is_a_Parking_arrangement;
    }

    public void setThere_is_a_Parking_arrangement(boolean there_is_a_Parking_arrangement) {
        this.there_is_a_Parking_arrangement = there_is_a_Parking_arrangement;
    }

    public boolean isThere_is_a_projector() {
        return There_is_a_projector;
    }

    public void setThere_is_a_projector(boolean there_is_a_projector) {
        There_is_a_projector = there_is_a_projector;
    }

    public boolean isThere_is_a_vidio_camera() {
        return there_is_a_vidio_camera;
    }

    public void setThere_is_a_vidio_camera(boolean there_is_a_vidio_camera) {
        this.there_is_a_vidio_camera = there_is_a_vidio_camera;
    }
}
