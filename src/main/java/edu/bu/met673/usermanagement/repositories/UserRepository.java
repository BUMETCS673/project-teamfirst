package edu.bu.met673.usermanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.bu.met673.usermanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.identityProviderId = :providerId")
    Optional<User> findByIdentityProviderId(@Param("providerId") String providerId);

}
