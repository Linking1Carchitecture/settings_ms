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
    private Long config_id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "subtitles")
    private boolean subtitles;
    @Column(name = "background_id", nullable = true)
    private Long background_id;
    @Column(name = "in_device")
    private String in_device;
    @Column(name = "out_device")
    private String out_device;

    public Configuration(Long user_id, boolean subtitles, String in_device, String out_device) {
        this.user_id = user_id;
        this.subtitles = subtitles;
        this.in_device = in_device;
        this.out_device = out_device;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public boolean getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(boolean subtitles) {
        this.subtitles = subtitles;
    }

    public Long getBackground_id() {
        return background_id;
    }

    public void setBackground_id(Long background_id) {
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
