package edu.bu.met673.usermanagement.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	private UserSummaryDto user;
}