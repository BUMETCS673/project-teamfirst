/**
 * 
 */
package edu.bu.met673.usermanagement.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * @author ajord
 *
 */
@Data
@MappedSuperclass
abstract class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@CreationTimestamp
	@Column(name="created_at")
	protected LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	protected LocalDateTime updatedAt;
}
