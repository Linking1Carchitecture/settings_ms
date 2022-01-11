package com.linking.settingsms.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer config_id;
    @Column(name = "user_email")
    private String user_email;
    @Column(name = "in_video", nullable = true)
    private String in_video;
    @Column(name = "background_id", nullable = true)
    private Integer background_id;
    @Column(name = "in_audio", nullable = true)
    private String in_audio;
    @Column(name = "out_audio", nullable = true)
    private String out_audio;

    public Configuration(String user_email, String in_video, String in_audio, String out_audio) {
        this.user_email = user_email;
        this.in_video = in_video;
        this.in_audio = in_audio;
        this.out_audio = out_audio;
    }

    public Configuration(String user_email) {
        this.user_email = user_email;
    }

    public Integer getConfig_id(){
        return config_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getIn_video() {
        return in_video;
    }

    public void setIn_video(String in_video) {
        this.in_video = in_video;
    }

    public Integer getBackground_id() {
        return background_id;
    }

    public void setBackground_id(Integer background_id) {
        this.background_id = background_id;
    }

    public String getIn_audio() {
        return in_audio;
    }

    public void setIn_audio(String in_audio) {
        this.in_audio = in_audio;
    }

    public String getOut_audio() {
        return out_audio;
    }

    public void setOut_audio(String out_audio) {
        this.out_audio = out_audio;
    }

    
}
