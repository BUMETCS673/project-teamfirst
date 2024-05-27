package edu.bu.met673.usermanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="UM_USER")
@Data
@EqualsAndHashCode(callSuper=true)
public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@Column(name="identity_provider_id")
	private String identityProviderId;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String username;
	private String city;
	private String state;
	@Column(name="postal_code")
	private String postalCode;
	private String country;
	
	@ManyToOne
    @JoinColumn(name="user_role_id", nullable=false)
	private UserRole role;
}
