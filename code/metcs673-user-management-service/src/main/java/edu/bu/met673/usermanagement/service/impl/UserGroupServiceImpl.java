/**
 * 
 */
package edu.bu.met673.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
