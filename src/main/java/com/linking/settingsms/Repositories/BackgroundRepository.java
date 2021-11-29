package com.linking.settingsms.Repositories;

import com.linking.settingsms.Model.Background;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface BackgroundRepository extends JpaRepository<Background, Long> {
    @Query(value = "SELECT * FROM background WHERE user_id=?1", nativeQuery = true)
    ArrayList<Background> findUserBackgrounds(Long user_id);

    @Modifying
    @Query(value = " UPDATE background SET imageName=?2 WHERE background_id=?1", nativeQuery = true)
    void updateImageName(Long background_id, String imageName);
}
