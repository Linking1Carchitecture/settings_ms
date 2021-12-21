package com.linking.settingsms.Controllers;

import com.linking.settingsms.Model.Configuration;
import com.linking.settingsms.Services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @GetMapping("/")
    public String start(){
        return "Welcome to Settings in Linking!";
    }

    @GetMapping("/settings")
    public ArrayList<Configuration> allConfig(){
        return configurationService.getAllConfig();
    }

    @GetMapping("/settings/{user_email}")
    public Configuration userConfig(@PathVariable("user_email") String user_email) {
        return configurationService.getConfigByUser(user_email);
    }

    @PostMapping("/settings/new")
    public Configuration newConfig(@RequestBody Configuration config){
        System.out.println("Configuration received - " + config.toString());
        if(config.getUser_email()!=null){
            return configurationService.newConfig(config);
        }
        return null;
    }

    @PutMapping("/settings/update")
    public ResponseEntity<?> changeConfig(@RequestBody Configuration config){
        if(configurationService.existsConfig(config.getConfig_id())){
            return configurationService.updateConfig(config);
        }
        return new ResponseEntity<>("User configuration not found", HttpStatus.BAD_REQUEST );
    }

    @PutMapping("/settings/{user_email}/changeBackground")
    public ResponseEntity<?> changeBackground(@PathVariable("user_email") String user_email, @RequestParam(value = "background_id", required = false) Integer background_id){
        Configuration config = configurationService.getConfigByUser(user_email);
        if(config != null) {
            if (background_id != null) {
                return configurationService.changeBackground(user_email, background_id);
            } else {
                return configurationService.noBackground(user_email);
            }
        }
        return new ResponseEntity<>("User configuration not found", HttpStatus.BAD_REQUEST );
    }

    @DeleteMapping("/settings/delete")
    public  ResponseEntity<?> deleteConfig(@RequestParam("config_id") Integer config_id){
        if(configurationService.existsConfig(config_id)){
            return  configurationService.deleteConfig(config_id);
        }
        return new ResponseEntity<>("Configuration not found", HttpStatus.BAD_REQUEST );
    }
}
