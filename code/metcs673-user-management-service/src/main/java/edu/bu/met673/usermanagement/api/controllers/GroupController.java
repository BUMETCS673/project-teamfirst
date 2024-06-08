package edu.bu.met673.usermanagement.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.service.UserGroupService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Observed
@RequestMapping("v1/groups")
@CrossOrigin(origins = "*")
@Tag(name = "Group Management", description = "APIs for managing group memberships[In progress...]")
public class GroupController {
	
	private static final String VIEW_PROFILE = "hasAuthority('read:view_profile')";
	
	private UserGroupService userGroupService;
	
	public GroupController(@Autowired UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	
	@PostMapping("/{groupId}/members")
	public ResponseEntity<UserDto> addusersToGroup(@PathVariable(name="groupId") String groupId, 
			@RequestBody List<String> userIds){
		return ResponseEntity.ok().body(null);
	}
	
	@PostMapping("/{groupId}/members/{userId}")
	public ResponseEntity<UserGroupDto> addUsetToGroup(@PathVariable(name="groupId") String groupId, 
			@PathVariable(name="userId") String userId){
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GroupDto> getGroupById(@PathVariable(name="id") String groupId){
		return ResponseEntity.ok().body(null);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GroupDto>> getAllGroups(@RequestParam(name="filter") String filter){
		return ResponseEntity.ok().body(null);
	}

	
	@PostMapping("/")
	public ResponseEntity<GroupDto> createUserGroup(@RequestBody GroupDto groupDto) {
		return ResponseEntity.ok().body(null);
	}

}
