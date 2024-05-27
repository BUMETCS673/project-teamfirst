package edu.bu.met673.usermanagement.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.service.UserService;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/{userId}/profile")
	@RolesAllowed({"CUSTOMER"})
	public ResponseEntity<UserDto> getProfile(@PathVariable(name="userId") Long userId){
		UserDto user = new UserDto();
		return ResponseEntity.ok().body(this.userService.registerUser(user));
	}

	@PutMapping("/{userId}/register")
	@RolesAllowed({"CUSTOMER"})
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
		return ResponseEntity.ok().body(this.userService.updateUser(userId,userDto));
	}

}
