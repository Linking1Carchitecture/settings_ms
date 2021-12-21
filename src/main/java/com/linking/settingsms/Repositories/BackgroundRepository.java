package com.linking.settingsms.Repositories;

import com.linking.settingsms.Model.Background;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface BackgroundRepository extends JpaRepository<Background, Integer> {
    @Query(value = "SELECT image_location FROM background WHERE user_email=?1", nativeQuery = true)
    ArrayList<String> findUserBackgrounds(String user_email);

    @Modifying
    @Query(value = "UPDATE background SET image_location=?2 WHERE background_id=?1", nativeQuery = true)
    void setImageLocation(Integer background_id, String image_location);

    @Modifying
    @Query(value = "DELETE FROM background WHERE user_email=?1", nativeQuery = true)
    void deleteByUserId(String user_email);
}
