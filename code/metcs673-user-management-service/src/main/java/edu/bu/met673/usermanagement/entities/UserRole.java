/**
 * 
 */
package edu.bu.met673.usermanagement.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author ajordany@bu.edu
 *
 */
@Entity
@Table(name="UM_USER_ROLE")
@Data
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	protected Integer id;
	private String name;
	
	@CreationTimestamp
	@Column(name="created_at")
	protected LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	protected LocalDateTime updatedAt;
	
}
