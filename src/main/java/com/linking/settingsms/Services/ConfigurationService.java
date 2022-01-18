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
        if (configurationRepository.findConfigByUser(config.getUser_email()) == null){
            return  configurationRepository.save(config);
        }
        return null;
    }

    public Configuration getConfigByUser(String user_email){
        return configurationRepository.findConfigByUser(user_email);
    }

    public ArrayList<Configuration> getAllConfig() {
        return (ArrayList<Configuration>) configurationRepository.findAll();
    }

    public ResponseEntity<?> updateConfig(Configuration newConfig) {
        configurationRepository.updateConfig(newConfig.getConfig_id(), newConfig.getIn_audio(), newConfig.getOut_audio(), newConfig.getIn_video());
        return new ResponseEntity<>("Settings updated", HttpStatus.OK);
    }

    public ResponseEntity<?> changeBackground(String user_email, Integer background_id ) {
        if(user_email != null){
            configurationRepository.updateBackground(user_email, background_id);
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

    public ResponseEntity<?> noBackground(String user_email) {
        configurationRepository.noBackground(user_email);
        return new ResponseEntity<>("Background set as Null", HttpStatus.OK);
    }
}
