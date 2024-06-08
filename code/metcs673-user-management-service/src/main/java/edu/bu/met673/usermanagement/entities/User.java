package edu.bu.met673.usermanagement.entities;

import java.util.List;

import edu.bu.met673.usermanagement.api.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="um_user")
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
	private String address;
	private String city;
	private String state;
	@Column(name="postal_code")
	private String postalCode;
	private String country;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_role")
	private UserRole userRole;
	
	@OneToMany(mappedBy="user")
	private List<UserGroup> userGroup;
}
