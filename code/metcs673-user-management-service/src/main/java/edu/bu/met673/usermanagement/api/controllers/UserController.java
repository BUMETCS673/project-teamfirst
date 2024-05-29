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
import io.micrometer.observation.annotation.Observed;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Observed
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/{userId}/profile")
	public ResponseEntity<UserDto> getProfile(@PathVariable(name="userId") Long userId){
		UserDto user = new UserDto();
		log.info("getProfile:::");
		return ResponseEntity.ok().body(this.userService.registerUser(user));
	}

	@PutMapping("/{userId}/register")
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
		log.info("updateUser:::");
		return ResponseEntity.ok().body(this.userService.updateUser(userId,userDto));
	}

}
