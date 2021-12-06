package com.linking.settingsms.Services;

import com.linking.settingsms.Model.Background;
import com.linking.settingsms.Repositories.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class BackgroundService {

    @Autowired
    BackgroundRepository backgroundRepository;

    public ArrayList<String> getUserBackgrounds(Integer user_id) {
        return backgroundRepository.findUserBackgrounds(user_id);
    }

    public Background getBackgroundById(Integer background_id) {
        return backgroundRepository.getById(background_id);
    }

    public Background newBackground(Integer user_id) {
        Background background = new Background(user_id);
        return backgroundRepository.save(background);
    }

    public void setBackgroundLocation(Integer background_id, String image_location) {
        backgroundRepository.setImageLocation(background_id, image_location);
    }

    public ResponseEntity<?> deleteBackground(Integer background_id) {
        if(backgroundRepository.existsById(background_id)){
            backgroundRepository.deleteById(background_id);
            return new ResponseEntity<>("Background deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Background not found", HttpStatus.NOT_FOUND );
    }

    public ResponseEntity<?> deleteUserBackgrounds(Integer user_id) {
        backgroundRepository.deleteByUserId(user_id);
        return new ResponseEntity<>("Backgrounds deleted!", HttpStatus.OK);
    }

    public ArrayList<Background> getAllBackgrounds() {
        return (ArrayList<Background>) backgroundRepository.findAll();
    }
}
