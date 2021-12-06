package com.linking.settingsms.Repositories;

import com.linking.settingsms.Model.Background;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface BackgroundRepository extends JpaRepository<Background, Integer> {
    @Query(value = "SELECT image_location FROM background WHERE user_id=?1", nativeQuery = true)
    ArrayList<String> findUserBackgrounds(Integer user_id);

    @Modifying
    @Query(value = "UPDATE background SET image_location=?2 WHERE background_id=?1", nativeQuery = true)
    void setImageLocation(Integer background_id, String image_location);

    @Modifying
    @Query(value = "DELETE FROM background WHERE user_id=?1", nativeQuery = true)
    void deleteByUserId(Integer user_id);
}
