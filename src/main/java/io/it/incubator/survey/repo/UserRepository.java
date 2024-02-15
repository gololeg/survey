package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByLoginAndPassword(String login, String password);
}
