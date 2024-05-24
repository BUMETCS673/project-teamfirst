/**
 * 
 */
package edu.bu.met673.usermanagement.api.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author ajord
 *
 */
@Data
@Valid
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class UserDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private long id;
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
  private String stripeId;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  private RoleDto role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
