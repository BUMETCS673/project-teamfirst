/**
 * UserGroupService interface for managing user groups within the system.
 * This service provides functionalities to create, delete, search for groups,
 * and manage user memberships within these groups.
 * 
 * @author ajordany
 */
package edu.bu.met673.usermanagement.service;

import java.util.List;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.api.model.UserSummaryDto;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;

public interface UserGroupService {

    /**
     * Creates a new user group based on the provided GroupDto.
     * 
     * @param groupDto - Data transfer object containing group details.
     * @return GroupDto - Details of the created group.
     * @throws ServiceException - If any service-related error occurs.
     */
    GroupDto createGroup(GroupDto groupDto) throws ServiceException;

    /**
     * Deletes an existing group by its ID.
     * 
     * @param groupId - ID of the group to be deleted.
     * @return GroupDto - Details of the deleted group.
     * @throws ServiceException - If any service-related error occurs.
     * @throws ResourceNotFoundException - If the group with the specified ID is not found.
     */
    GroupDto deleteGroup(Long groupId) throws ServiceException, ResourceNotFoundException;

    /**
     * Searches for groups based on the provided filter string.
     * 
     * @param filter - Filter criteria for searching groups.
     * @return List<GroupDto> - List of groups matching the filter criteria.
     * @throws ServiceException - If any service-related error occurs.
     */
    List<GroupDto> searchGroups(String filter) throws ServiceException;

    /**
     * Adds a user to a specified group.
     * 
     * @param userId - ID of the user to be added.
     * @param groupId - ID of the group to which the user will be added.
     * @return UserGroupDto - Details of the user-group relationship.
     * @throws ServiceException - If any service-related error occurs.
     * @throws ResourceNotFoundException - If either the user or group is not found.
     */
    UserGroupDto addUserToGroup(Long userId, Long groupId) throws ServiceException, ResourceNotFoundException;

    /**
     * Adds multiple users to a specified group.
     * 
     * @param userIds - List of IDs of the users to be added.
     * @param groupId - ID of the group to which the users will be added.
     * @return List<UserGroupDto> - List of details of user-group relationships.
     * @throws ServiceException - If any service-related error occurs.
     * @throws ResourceNotFoundException - If any of the users or the group is not found.
     */
    List<UserGroupDto> addUsersToGroup(List<Long> userIds, Long groupId) throws ServiceException, ResourceNotFoundException;

    /**
     * Retrieves the details of a group by its ID.
     * 
     * @param groupId - ID of the group to be retrieved.
     * @return GroupDto - Details of the retrieved group.
     * @throws ServiceException - If any service-related error occurs.
     * @throws ResourceNotFoundException - If the group with the specified ID is not found.
     */
    GroupDto getGroupById(Long groupId) throws ServiceException, ResourceNotFoundException;

    /**
     * Retrieves the members of a specific group.
     * 
     * @param groupId - ID of the group whose members are to be retrieved.
     * @return List<UserSummaryDto> - List of summaries of group members.
     * @throws ServiceException - If any service-related error occurs.
     * @throws ResourceNotFoundException - If the group with the specified ID is not found.
     */
    List<UserSummaryDto> getMembersForGivenGroup(Long groupId) throws ServiceException, ResourceNotFoundException;

}