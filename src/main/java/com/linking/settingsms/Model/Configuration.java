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
    @Column(name = "subtitles")
    private boolean subtitles;
    @Column(name = "background_id", nullable = true)
    private Integer background_id;
    @Column(name = "in_device", nullable = true)
    private String in_device;
    @Column(name = "out_device", nullable = true)
    private String out_device;

    public Configuration(String user_email, boolean subtitles, String in_device, String out_device) {
        this.user_email = user_email;
        this.subtitles = subtitles;
        this.in_device = in_device;
        this.out_device = out_device;
    }

    public Configuration(String user_email) {
        this.user_email = user_email;
        this.subtitles = false;
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

    public boolean getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(boolean subtitles) {
        this.subtitles = subtitles;
    }

    public Integer getBackground_id() {
        return background_id;
    }

    public void setBackground_id(Integer background_id) {
        this.background_id = background_id;
    }

    public String getIn_device() {
        return in_device;
    }

    public void setIn_device(String in_device) {
        this.in_device = in_device;
    }

    public String getOut_device() {
        return out_device;
    }

    public void setOut_device(String out_device) {
        this.out_device = out_device;
    }   
}
