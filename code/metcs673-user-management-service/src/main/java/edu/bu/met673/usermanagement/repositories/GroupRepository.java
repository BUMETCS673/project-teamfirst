/**
 * 
 */
package edu.bu.met673.usermanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.bu.met673.usermanagement.entities.Group;

/**
 * 
 */
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long>{
	
	List<Group> findByNameContaining(String filter);
	
	@Query("SELECT g FROM Group g WHERE lower(g.name) LIKE lower(concat('%', :filter, '%'))")
	List<Group> search(@Param("filter") String filter);

}
