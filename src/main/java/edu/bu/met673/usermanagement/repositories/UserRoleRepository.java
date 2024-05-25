package edu.bu.met673.usermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.bu.met673.usermanagement.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    
	@Query("select r from UserRole r where r.name =:name")
    UserRole findByName(@Param("name") String name);
}
