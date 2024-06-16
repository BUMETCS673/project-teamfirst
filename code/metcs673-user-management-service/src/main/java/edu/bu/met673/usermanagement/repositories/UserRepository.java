package edu.bu.met673.usermanagement.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.bu.met673.usermanagement.entities.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.identityProviderId = :providerId")
    Optional<User> findByIdentityProviderId(@Param("providerId") String providerId);
 
    User findByUsername(String username);
    
    User findByEmail(String email);
    
    @Query(
	"SELECT u FROM User u WHERE " +
    ":filter IS NULL " +
    "OR u.firstname LIKE %:filter% " +
    "OR u.lastname LIKE %:filter% " +
    "OR u.username LIKE %:filter% " +
    "OR u.email LIKE %:filter% ")
    Page<User> findUsers(@Param("filter") String filter, Pageable peagable);
}
