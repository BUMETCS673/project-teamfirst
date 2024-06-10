/**
 * 
 */
package edu.bu.met673.usermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.bu.met673.usermanagement.entities.Group;

/**
 * 
 */
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long>{

}
