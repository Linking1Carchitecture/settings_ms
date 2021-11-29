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
        return "Welcome to Linking!";
    }

    @GetMapping("/settings")
    public ArrayList<Configuration> allConfig(){
        return configurationService.getAllConfig();
    }

    @GetMapping("/settings/{user_id}")
    public Configuration userConfig(@PathVariable("user_id") Long user_id) {
        return configurationService.getConfigByUser(user_id);
    }

    @PostMapping("/settings/new")
    public Configuration newConfig(@RequestBody Configuration config){
        if(config.getUser_id()!=null){
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

    @PutMapping("/settings/{user_id}/changeBackground")
    public ResponseEntity<?> changeBackground(@PathVariable("user_id") Long user_id, @RequestParam(value = "background_id", required = false) Long background_id){
        Configuration config = configurationService.getConfigByUser(user_id);
        if(config != null) {
            if (background_id != null) {
                return configurationService.changeBackground(user_id, background_id);
            } else {
                return configurationService.noBackground(user_id);
            }
        }
        return new ResponseEntity<>("User configuration not found", HttpStatus.BAD_REQUEST );
    }

    @DeleteMapping("/settings/delete")
    public  ResponseEntity<?> deleteConfig(@RequestParam("config_id") Long config_id){
        if(configurationService.existsConfig(config_id)){
            return  configurationService.deleteConfig(config_id);
        }
        return new ResponseEntity<>("Configuration not found", HttpStatus.BAD_REQUEST );
    }
}
