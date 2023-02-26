package com.example.usermanager.dao;

import com.example.usermanager.entity.Setting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Setting findAllByUserEmailAndKey(String email, String key);
}
