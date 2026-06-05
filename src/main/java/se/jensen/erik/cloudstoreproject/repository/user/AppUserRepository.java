package se.jensen.erik.cloudstoreproject.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.erik.cloudstoreproject.model.user.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String mail);
    boolean existsByEmail(String mail);

}
