/**
 * 
 */
package edu.bu.met673.usermanagement.api.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class GroupDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private String name;
	private String description;
	private String createdBy;
	private List<UserSummaryDto> members;
}
