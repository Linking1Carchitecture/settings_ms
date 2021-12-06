package com.linking.settingsms.Controllers;

import com.linking.settingsms.Model.Background;
import com.linking.settingsms.Services.BackgroundService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

@RestController
public class BackgroundController {

    @Autowired
    BackgroundService backgroundService;

    private String FTP_ADDRESS="vsftpd";
    private int FTP_PORT = 21;
    private String FTP_USER = "myuser";
    private String FTP_PASSWORD = "mypass";
    private FTPClient con = null;

    @GetMapping("/backgrounds")
    public ArrayList<Background> getAllBackgrounds(){
        return backgroundService.getAllBackgrounds();
    }

    @GetMapping("/backgrounds/{background_id}")
    public String getBackground(@PathVariable("background_id") Integer background_id) throws Exception {
        Background background = backgroundService.getBackgroundById(background_id);
        try {
            con = new FTPClient();
            con.connect(FTP_ADDRESS, FTP_PORT);
            if (con.login(FTP_USER, FTP_PASSWORD)) {
                con.enterLocalPassiveMode(); // important!
                ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
                boolean success = con.retrieveFile(background.getImageLocation(), imageBytes);
                if (success) {
                    return Base64.getEncoder().encodeToString(imageBytes.toByteArray());
                }
                throw new Exception("Bad credentials!");
            }
        } catch (Exception e) {
            throw new Exception("Could not get background!\n"+e);
        }
        return "Nothing found!";
    }

    @GetMapping("/backgrounds/user")
    public ArrayList<String> getUserBackgrounds(@RequestParam("id") Integer user_id) throws Exception {
        try {
            con = new FTPClient();
            con.connect(FTP_ADDRESS, FTP_PORT);
            if (con.login(FTP_USER, FTP_PASSWORD)) {
                con.enterLocalPassiveMode(); // important!
                ArrayList<String> userBackgrounds = new ArrayList<>();

                for (String location : backgroundService.getUserBackgrounds(user_id)) {

                    ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
                    boolean success = con.retrieveFile(location, imageBytes);
//                    System.out.println(con.getReplyString());
//                    System.out.println(location);
//                    System.out.println(success);
                    if (success) {
                        String encoded = Base64.getEncoder().encodeToString(imageBytes.toByteArray());
                        userBackgrounds.add(encoded);
                    }
                }
//                System.out.println("Array Size: "+userBackgrounds.size());
                return userBackgrounds;
            }
            throw new Exception("Bad credentials!");
        } catch (Exception e) {
            throw new Exception("Could not get user backgrounds!\n"+e);
        }
    }

    @PostMapping("/backgrounds/{user_id}/new")
    public ResponseEntity<?>  newBackground(@PathVariable("user_id") Integer user_id, @RequestParam("image")MultipartFile imageFile){
        System.out.println("Just called: "+imageFile.getSize());
        try {
            con = new FTPClient();
            con.connect(FTP_ADDRESS,FTP_PORT);
            con.setRemoteVerificationEnabled(false);

            if (con.login(FTP_USER, FTP_PASSWORD)) {
                con.enterLocalPassiveMode(); // important!
                boolean test = con.setFileType(FTP.BINARY_FILE_TYPE);
                con.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                System.out.println(test);
                String location = "/backgrounds";
                boolean dirExists = con.changeWorkingDirectory(location);
                if (!dirExists) {
                    con.makeDirectory(location);
                }
                location = "/backgrounds/"+user_id;
                dirExists = con.changeWorkingDirectory(location);
                if (!dirExists) {
                    con.makeDirectory(location);
                }
                Background background = backgroundService.newBackground(user_id);
                String imageExtension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
                String image_location = location+"/"+background.getBackground_id()+"."+imageExtension;
                System.out.println("Before store: "+imageFile.getSize());
                boolean result = con.storeFile(image_location, imageFile.getInputStream());
                System.out.println("Result: "+result+"   "+con.getReplyString());
                System.out.println("After store: "+imageFile.getSize());
                backgroundService.setBackgroundLocation(background.getBackground_id(), image_location);
                con.logout();
                con.disconnect();
                return new ResponseEntity<>("You successfully uploaded " + imageFile.getOriginalFilename() + "!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Bad credentials!", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not upload " + imageFile.getOriginalFilename() + "!\n"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/backgrounds/delete")
    public  ResponseEntity<?> deleteBackground(@RequestParam("background_id") Integer background_id){
        try {
            con = new FTPClient();
            con.connect(FTP_ADDRESS, FTP_PORT);
            Background background = backgroundService.getBackgroundById(background_id);
            if (con.login(FTP_USER, FTP_PASSWORD)) {
                String image_location = background.getImageLocation();
                con.deleteFile(image_location);
                con.removeDirectory("backgrounds/"+background.getUser_id());
                con.logout();
                con.disconnect();
                return  backgroundService.deleteBackground(background_id);
            }
            return new ResponseEntity<>("Bad credentials!", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not delete background!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/backgrounds/delete/{user_id}")
    public  ResponseEntity<?> deleteUserBackgrounds(@PathVariable("user_id") Integer user_id){
        try {
            con = new FTPClient();
            con.connect(FTP_ADDRESS, FTP_PORT);
            if (con.login(FTP_USER, FTP_PASSWORD)) {
                for (String location : backgroundService.getUserBackgrounds(user_id)) {
                    con.deleteFile(location);
                }
                con.removeDirectory("backgrounds/"+user_id);
                con.logout();
                con.disconnect();
                return backgroundService.deleteUserBackgrounds(user_id);
            }
            return new ResponseEntity<>("Bad credentials!", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not delete backgrounds!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
