package edu.bu.met673.usermanagement.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 * The persistent class for the um_user_group database table.
 * 
 */
@Entity
@Table(name="um_user_group")
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="joined_at")
	private Timestamp joinedAt;

	@Column(name="role")
	private String gorupRole;

	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}