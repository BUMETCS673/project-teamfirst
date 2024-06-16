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
 * @author ajord
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class UserSummaryDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long id;
  private String identityProviderId;
  private String username;
  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
}
