/**
 * 
 */
package edu.bu.met673.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.repositories.GroupRepository;
import edu.bu.met673.usermanagement.service.UserGroupService;

/**
 * 
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {
	
	private GroupRepository groupRepository;
	
	public UserGroupServiceImpl(@Autowired GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public GroupDto createGroup(GroupDto groupDto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupDto deleteGroup(Long groupId) throws ServiceException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
