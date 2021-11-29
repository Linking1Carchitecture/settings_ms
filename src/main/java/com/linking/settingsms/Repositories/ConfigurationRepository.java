package com.linking.settingsms.Repositories;

import com.linking.settingsms.Model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    @Query(value = "SELECT * FROM configuration WHERE user_id=?1 LIMIT 1", nativeQuery = true)
    Configuration findConfigByUser(Long user_id);

    @Modifying
    @Query(value = " UPDATE configuration SET background_id=?2 WHERE user_id=?1", nativeQuery = true)
    void updateBackground(Long user_id, Long background_id);

    @Modifying
    @Query(value = " UPDATE configuration SET subtitles=?2, in_device=?3, out_device=?4 WHERE config_id=?1", nativeQuery = true)
    void updateConfig(Long config_id, boolean subtitles, String in_device, String out_device);

    @Modifying
    @Query(value = " UPDATE configuration SET background_id=NULL WHERE user_id=?1", nativeQuery = true)
    void noBackground(Long user_id);
}