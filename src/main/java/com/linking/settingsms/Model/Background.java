package com.linking.settingsms.Model;

import javax.persistence.*;

@Entity
@Table(name = "background")
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long background_id;
    @Column (name = "user_id")
    private Long user_id;
    @Column (name = "imageName")
    private String imageName;
    @Lob
    @Column (name = "image")
    private byte[] image;

    public Background(){}

    public Background(Long user_id, String imageName, byte[] image){
        this.user_id = user_id;
        this.imageName = imageName;
        this.image = image;
    }

    public Long getBackground_id() {
        return background_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
