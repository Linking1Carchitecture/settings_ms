package com.linking.settingsms.Model;

import javax.persistence.*;

@Entity
@Table(name = "background")
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer background_id;
    @Column (name = "user_id")
    private Integer user_id;
    @Column (name = "image_location")
    private String image_location;

    public Background(){}

    public Background(Integer user_id){
        this.user_id = user_id;
    }

    public Background(Integer user_id, String image_location){
        this.user_id = user_id;
        this.image_location = image_location;
    }

    public Integer getBackground_id() {
        return background_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getImageLocation() {
        return image_location;
    }

    public void setImageLocation(String image_location) {
        this.image_location = image_location;
    }
}
