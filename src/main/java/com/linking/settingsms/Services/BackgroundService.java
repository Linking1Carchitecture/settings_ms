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

    public ArrayList<Background> getUserBackgrounds(Long user_id) {
        return backgroundRepository.findUserBackgrounds(user_id);
    }

    public Background newBackground(Long user_id, String imageName, byte[] image) {
        Background background = new Background(user_id, imageName, image);
        return backgroundRepository.save(background);
    }

    public ResponseEntity<?> updateImageName(Long background_id, String imageName) {
        if(backgroundRepository.existsById(background_id)){
            backgroundRepository.updateImageName(background_id, imageName);
            return new ResponseEntity<>("Background name updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Background not found", HttpStatus.NOT_FOUND );
    }

    public ResponseEntity<?> deleteBackground(Long background_id) {
        if(backgroundRepository.existsById(background_id)){
            backgroundRepository.deleteById(background_id);
            return new ResponseEntity<>("Background deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Background not found", HttpStatus.NOT_FOUND );
    }

    public ArrayList<Background> getAllBackgrounds() {
        return (ArrayList<Background>) backgroundRepository.findAll();
    }
}
