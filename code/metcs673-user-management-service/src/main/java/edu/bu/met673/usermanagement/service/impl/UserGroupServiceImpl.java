/**
 * 
 */
package edu.bu.met673.usermanagement.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.api.model.UserSummaryDto;
import edu.bu.met673.usermanagement.entities.Group;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.entities.UserGroup;
import edu.bu.met673.usermanagement.exceptions.Errors;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.mapper.UserGroupMapper;
import edu.bu.met673.usermanagement.mapper.UserMapper;
import edu.bu.met673.usermanagement.repositories.GroupRepository;
import edu.bu.met673.usermanagement.repositories.UserGroupRepository;
import edu.bu.met673.usermanagement.repositories.UserRepository;
import edu.bu.met673.usermanagement.service.UserGroupService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Transactional
@Slf4j
@Service
public class UserGroupServiceImpl implements UserGroupService {
	private GroupRepository groupRepository;
	private UserGroupRepository userGroupRepository;
	private UserRepository userRepository;

	public UserGroupServiceImpl(GroupRepository groupRepository, UserGroupRepository userGroupRepository,
			UserRepository userRepository) {
		this.groupRepository = groupRepository;
		this.userGroupRepository = userGroupRepository;
		this.userRepository = userRepository;
	}

	@Override
	public GroupDto createGroup(GroupDto groupDto) throws ServiceException {
		try {
			Group group = UserGroupMapper.toGroupEntity(groupDto);
			Group savedGroup = groupRepository.save(group);
			return UserGroupMapper.toGroupDto(savedGroup);
		} catch (Exception ex) {
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public GroupDto deleteGroup(Long groupId) throws ServiceException, ResourceNotFoundException {
		try {
			Group group = groupRepository.findById(groupId)
					.orElseThrow(() -> new ResourceNotFoundException(Errors.RESOURCE_NOT_FOUND));
			groupRepository.delete(group);
			return UserGroupMapper.toGroupDto(group);

		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public List<GroupDto> searchGroups(String filter) throws ServiceException {
		try {
			List<Group> groups = null;
			if(Objects.isNull(filter) || filter.isEmpty()) {
				groups = this.groupRepository.findAll();
			}else {
				groups = groupRepository.search(filter);
			}
			
			return groups.stream().map(UserGroupMapper::toGroupDto).toList();
		} catch (Exception ex) {
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public UserGroupDto addUserToGroup(Long userId, Long groupId) throws ServiceException, ResourceNotFoundException {
		try {
			Group group = groupRepository.findById(groupId)
					.orElseThrow(() -> new ResourceNotFoundException(Errors.RESOURCE_NOT_FOUND));

			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException(Errors.INVALID_USER_ACCOUNT));

			UserGroup userGroup = new UserGroup();
			userGroup.setGroup(group);
			userGroup.setUser(user);
			UserGroup savedUserGroup = userGroupRepository.save(userGroup);
			return UserGroupMapper.toUserGroupDto(savedUserGroup);

		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public List<UserGroupDto> addUsersToGroup(List<Long> userIds, Long groupId)
			throws ServiceException, ResourceNotFoundException {
		try {
			Group group = groupRepository.findById(groupId)
					.orElseThrow(() -> new ResourceNotFoundException(Errors.RESOURCE_NOT_FOUND));

			List<User> users = userRepository.findAllById(userIds);

			if (users.size() != userIds.size()) {
				throw new ResourceNotFoundException(Errors.INVALID_USER_ACCOUNT);
			}

			List<UserGroup> userGroups = users.stream().map(user -> {
				UserGroup userGroup = new UserGroup();
				userGroup.setGroup(group);
				userGroup.setUser(user);
				return userGroup;
			}).toList();

			List<UserGroup> savedUserGroups = userGroupRepository.saveAll(userGroups);
			return savedUserGroups.stream().map(UserGroupMapper::toUserGroupDto).toList();
		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public GroupDto getGroupById(Long groupId) throws ServiceException, ResourceNotFoundException {
		try {
			Group group = groupRepository.findById(groupId)
					.orElseThrow(() -> new ResourceNotFoundException(Errors.RESOURCE_NOT_FOUND));
			return UserGroupMapper.toGroupDto(group);
		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public List<UserSummaryDto> getMembersForGivenGroup(Long groupId) throws ServiceException, ResourceNotFoundException {
		try {
			List<UserGroup> userGroups = userGroupRepository.findUserGroupsByGroupId(groupId);
			if (userGroups.isEmpty()) {
				throw new ResourceNotFoundException(Errors.RESOURCE_NOT_FOUND);
			}
			return userGroups.stream().map(userGroup-> UserMapper.toUserSummaryDto(userGroup.getUser())).toList();
		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}
}
