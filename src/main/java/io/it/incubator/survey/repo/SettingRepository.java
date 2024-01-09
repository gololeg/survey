package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SettingRepository extends JpaRepository<Setting, String> {

}
