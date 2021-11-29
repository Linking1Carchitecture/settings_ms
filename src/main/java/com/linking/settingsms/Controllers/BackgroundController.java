package com.linking.settingsms.Controllers;

import com.linking.settingsms.Model.Background;
import com.linking.settingsms.Services.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class BackgroundController {

    @Autowired
    BackgroundService backgroundService;

    @GetMapping("/backgrounds")
    public ArrayList<Background> getAllBackgrounds(){
        return backgroundService.getAllBackgrounds();
    }

    @GetMapping("/backgrounds/{user_id}")
    public ArrayList<Background> getUserBackgrounds(@PathVariable("user_id") Long user_id){
        return backgroundService.getUserBackgrounds(user_id);
    }

    @PostMapping("/backgrounds/{user_id}/new")
    public Background newBackground(@PathVariable("user_id") Long user_id, @RequestParam("imageName") String imageName, @RequestParam("image")MultipartFile imageFile) throws IOException {
        byte[] image = imageFile.getBytes();
        return backgroundService.newBackground(user_id, imageName, image);
    }

    @PutMapping("/backgrounds/changeName")
    public ResponseEntity<?> changeBackgroundName(@RequestParam("background_id") Long background_id, @RequestParam("imageName") String imageName){
        return backgroundService.updateImageName(background_id, imageName);
    }

    @DeleteMapping("/backgrounds/delete")
    public  ResponseEntity<?> deleteBackground(@RequestParam("background_id") Long background_id){
        return  backgroundService.deleteBackground(background_id);
    }

}
