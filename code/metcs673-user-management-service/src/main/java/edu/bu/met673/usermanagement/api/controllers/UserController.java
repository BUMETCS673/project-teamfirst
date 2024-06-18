package edu.bu.met673.usermanagement.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bu.met673.usermanagement.api.model.PageData;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.config.Permissions;
import edu.bu.met673.usermanagement.service.UserService;
import edu.bu.met673.usermanagement.utils.PageRequestUtils;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Observed
@RequestMapping("v1/user-profiles")
@CrossOrigin(origins = "*")
@Tag(name = "User Management", description = "APIs for managing user profiles")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize(Permissions.VIEW_PROFILE)
    @Operation(summary = "Get current user profile", description = "Fetches the profile information of the currently authenticated user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<UserDto> getMyProfile() {
        return ResponseEntity.ok().body(this.userService.getMyProfile());
    }

    @GetMapping("/{id}")
    @PreAuthorize(Permissions.VIEW_PROFILE)
    @Operation(summary = "Get user profile by ID", description = "Fetches the profile information of a user by their ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDto> getUserProfileById(
            @Parameter(description = "ID of the user whose profile is to be retrieved") @PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok().body(this.userService.getUserProfile(userId));
    }

    @GetMapping("")
    @PreAuthorize(Permissions.VIEW_PROFILE)
    @Operation(summary = "Get all user profiles", description = "Fetches profiles of users with optional filters and pagination.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profiles retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageData.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<PageData<UserDto>> getUserProfiles(
            @Parameter(description = "Filter criteria for profiles") @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "100", name = "size") int size,
            @RequestParam(defaultValue = "id,desc", name = "sort") String[] sort) {
        return ResponseEntity.ok().body(this.userService.getAllUserProfiles(filter, PageRequestUtils.getPageRequest(page, size, sort)));
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<UserDto> register(
            @Parameter(description = "Details of the user to be registered") @RequestBody UserRegistrationRequest registrationRequest) {
        return ResponseEntity.ok().body(this.userService.register(registrationRequest));
    }
    
    @PatchMapping("/")
    @Operation(summary = "Update a new user", description = "Update  a new user profile with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<UserDto> updateMyProfile(
    		@Parameter(description = "User ID") @PathVariable(name = "id") Long userId,
            @Parameter(description = "Details of the user to be updated") @RequestBody UserRegistrationRequest registrationRequest) {
        return ResponseEntity.ok().body(this.userService.updateUserProfile(userId, registrationRequest));
    }
}
