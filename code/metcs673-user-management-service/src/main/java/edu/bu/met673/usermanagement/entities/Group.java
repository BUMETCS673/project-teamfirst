package edu.bu.met673.usermanagement.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="um_group")
@Data
@EqualsAndHashCode(callSuper=true)
public class Group extends BaseEntity{
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	@Column(name="created_by")
	private String createdBy;
	
	//bi-directional many-to-one association to UmUserGroup
	@OneToMany(mappedBy="group")
	private List<UserGroup> userGroups;
}
