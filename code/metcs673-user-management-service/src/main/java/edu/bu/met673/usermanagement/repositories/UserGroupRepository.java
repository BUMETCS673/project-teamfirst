/**
 * 
 */
package edu.bu.met673.usermanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.bu.met673.usermanagement.entities.UserGroup;

/**
 * 
 */
@Transactional
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{
	
	// Alternatively, using JPQL if you need a more complex query
    @Query("SELECT ug FROM UserGroup ug WHERE ug.group.id = :groupId")
    List<UserGroup> findUserGroupsByGroupId(@Param("groupId") Long groupId);
    
    @Query("SELECT ug FROM UserGroup ug WHERE ug.group.id = :groupId and ug.user.id = :userId")
    Optional<UserGroup> findByGroupIdAndUserId(@Param("groupId") Long groupId, @Param("userId") Long userId);

}
