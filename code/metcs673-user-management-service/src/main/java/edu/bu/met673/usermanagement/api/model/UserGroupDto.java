package edu.bu.met673.usermanagement.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 * The persistent class for the um_user_group database table.
 * 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class UserGroupDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp joinedAt;
	private String gorupRole;
	private GroupDto group;
	private UserSummaryDto user;
}