/**
 * 
 */
package edu.bu.met673.usermanagement.service;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;

/**
 * 
 */
public interface UserGroupService {
	
	
	GroupDto createGroup(GroupDto groupDto) throws ServiceException;
	
	
	GroupDto deleteGroup(Long groupId) throws ServiceException, ResourceNotFoundException;
	
	

}
