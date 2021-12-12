package com.linking.settingsms.Services;

import com.linking.settingsms.Model.Configuration;
import com.linking.settingsms.Repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class ConfigurationService {

    @Autowired
    ConfigurationRepository configurationRepository;

    public Configuration newConfig(Configuration config) {
        return  configurationRepository.save(config);
    }

    public Configuration getConfigByUser(Integer user_id){
        return configurationRepository.findConfigByUser(user_id);
    }

    public ArrayList<Configuration> getAllConfig() {
        return (ArrayList<Configuration>) configurationRepository.findAll();
    }

    public ResponseEntity<?> updateConfig(Configuration newConfig) {
        configurationRepository.updateConfig(newConfig.getConfig_id(), newConfig.getSubtitles(), newConfig.getIn_device(), newConfig.getOut_device());
        return new ResponseEntity<>("Settings updated", HttpStatus.OK);
    }

    public ResponseEntity<?> changeBackground(Integer user_id, Integer background_id ) {
        if(user_id != null){
            configurationRepository.updateBackground(user_id, background_id);
            return new ResponseEntity<>("Background updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("User configuration not found", HttpStatus.BAD_REQUEST );
    }

    public ResponseEntity<?> deleteConfig(Integer config_id) {
        configurationRepository.deleteById(config_id);
        return new ResponseEntity<>("Configuration deleted", HttpStatus.OK);
    }

    public  boolean existsConfig(Integer config_id){
        return configurationRepository.existsById(config_id);
    }

    public ResponseEntity<?> noBackground(Integer user_id) {
        configurationRepository.noBackground(user_id);
        return new ResponseEntity<>("Background set as Null", HttpStatus.OK);
    }
}
