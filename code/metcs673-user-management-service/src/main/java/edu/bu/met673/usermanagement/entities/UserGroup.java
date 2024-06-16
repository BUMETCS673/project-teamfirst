package edu.bu.met673.usermanagement.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * The persistent class for the um_user_group database table.
 * 
 */
@Data
@Entity
@Table(name="um_user_group")
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
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