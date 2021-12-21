package com.linking.settingsms.Model;

import javax.persistence.*;

@Entity
@Table(name = "background")
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer background_id;
    @Column (name = "user_email")
    private String user_email;
    @Column (name = "image_location")
    private String image_location;

    public Background(){}

    public Background(String user_email){
        this.user_email = user_email;
    }

    public Background(String user_email, String image_location){
        this.user_email = user_email;
        this.image_location = image_location;
    }

    public Integer getBackground_id() {
        return background_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getImageLocation() {
        return image_location;
    }

    public void setImageLocation(String image_location) {
        this.image_location = image_location;
    }
}
