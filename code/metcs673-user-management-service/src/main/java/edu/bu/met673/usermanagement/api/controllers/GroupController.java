package edu.bu.met673.usermanagement.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.api.model.UserSummaryDto;
import edu.bu.met673.usermanagement.config.Permissions;
import edu.bu.met673.usermanagement.service.UserGroupService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Observed
@RequestMapping("v1/groups")
@CrossOrigin(origins = "*")
@Tag(name = "Group Management", description = "APIs for managing group memberships")
public class GroupController {
    
    private UserGroupService userGroupService;
    
    public GroupController(@Autowired UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }
    
    @Operation(summary = "Add users to a group", description = "Endpoint to add multiple users to a group.")
    @PostMapping("/{groupId}/members")
    public ResponseEntity<List<UserGroupDto>> addUsersToGroup(@PathVariable(name="groupId") Long groupId, 
            @RequestBody List<Long> userIds) {
        return ResponseEntity.ok().body(this.userGroupService.addUsersToGroup(userIds, groupId));
    }
    
    @Operation(summary = "Add a user to a group", description = "Endpoint to add a single user to a group.")
    @PreAuthorize(Permissions.MANAGE_GROUP_MEMBERS)
    @PostMapping("/{groupId}/members/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserGroupDto> addUserToGroup(@PathVariable(name="groupId") Long groupId, 
            @PathVariable(name="userId") Long userId) {
        return ResponseEntity.ok().body(this.userGroupService.addUserToGroup(userId, groupId));
    }

    @Operation(summary = "Get group by ID", description = "Endpoint to retrieve a group by its ID.")
    @PreAuthorize(Permissions.VIEW_GROUP)
    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById( @Parameter(description = "ID of the group to be obtained") 
    							@PathVariable(name="id") Long groupId) {
        return ResponseEntity.ok().body(this.userGroupService.getGroupById(groupId));
    }
    
    @Operation(summary = "Get Members for a given group", description = "Endpoint to retrieve the members for a given group.")
    @PreAuthorize(Permissions.VIEW_GROUP)
    @GetMapping("/{id}/members")
    public ResponseEntity<List<UserSummaryDto>> getMembersForGivenGroup( @Parameter(description = "ID of the group to be obtained") 
    							@PathVariable(name="id") Long groupId) {
        return ResponseEntity.ok().body(this.userGroupService.getMembersForGivenGroup(groupId));
    }
    
    @Operation(summary = "Get all groups", description = "Endpoint to retrieve all groups with optional filtering.")
    @PreAuthorize(Permissions.VIEW_GROUP)
    @GetMapping("/")
    public ResponseEntity<List<GroupDto>> getAllGroups(
            @Parameter(description = "Filter criteria") @RequestParam(name="filter", defaultValue = "") String filter) {
        return ResponseEntity.ok().body(this.userGroupService.searchGroups(filter));
    }

    @Operation(summary = "Create a user group", description = "Endpoint to create a new user group.")
    @PreAuthorize(Permissions.CREATE_GROUP)
    @PostMapping("/")
    public ResponseEntity<GroupDto> createUserGroup(
            @RequestBody GroupDto groupDto) {
        return ResponseEntity.ok().body(this.userGroupService.createGroup(groupDto));
    }
    
    @Operation(summary = "Delete a user group", description = "Endpoint to delete a new user group.")
    @PreAuthorize(Permissions.MANAGE_DELETE_GROUP)
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> createUserGroup(
    		@Parameter(description = "ID of the group to be obtained") 
			@PathVariable(name="id") Long groupId) {
        return ResponseEntity.ok().body(this.userGroupService.deleteGroup(groupId));
    }
    
}