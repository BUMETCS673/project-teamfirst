/**
 * 
 */
package edu.bu.met673.usermanagement.api.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class UserRegistrationRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String identityProviderId;
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dob;
	private String phone;
	
	@NotEmpty
	private String email;
	private Timestamp emailVerifiedAt;
	private String username;
	private String address;
	private String city;
	private String state;
	private UserRole userRole;
	private String postalCode;
	private String country;
}
