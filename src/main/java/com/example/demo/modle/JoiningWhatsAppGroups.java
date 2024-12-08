package com.example.demo.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JoiningWhatsAppGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;
    private String nameWhatsAppGroups;
    private String discriptionWhatsAppGroups;

    //לינק שהוא בעצם בנוי כבר..
    private String url_joiningWhatsAppGroups;

    //get&&set

    public String getUrl_joiningWhatsAppGroups() {
        return url_joiningWhatsAppGroups;
    }

    public void setUrl_joiningWhatsAppGroups(String url_joiningWhatsAppGroups) {
        this.url_joiningWhatsAppGroups = url_joiningWhatsAppGroups;
    }

    public String getDiscriptionWhatsAppGroups() {
        return discriptionWhatsAppGroups;
    }

    public void setDiscriptionWhatsAppGroups(String discriptionWhatsAppGroups) {
        this.discriptionWhatsAppGroups = discriptionWhatsAppGroups;
    }

    public String getNameWhatsAppGroups() {
        return nameWhatsAppGroups;
    }

    public void setNameWhatsAppGroups(String nameWhatsAppGroups) {
        this.nameWhatsAppGroups = nameWhatsAppGroups;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
