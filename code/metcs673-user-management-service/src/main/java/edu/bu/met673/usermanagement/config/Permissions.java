/**
 * 
 */
package edu.bu.met673.usermanagement.config;

/**
 * 
 */
public final class Permissions {
	
	private Permissions() {}
	
	public static final String VIEW_PROFILE = "hasAuthority('read:view_profile')";
	public static final String UPDATE_PROFILE = "hasAuthority('create:update_profile')";
	public static final String CREATE_GROUP = "hasAuthority('create:create_group')";
	public static final String MANAGE_GROUP_MEMBERS = "hasAuthority('create:manage_members')";
	public static final String MANAGE_DELETE_GROUP = "hasAuthority('create:delete_group')";
	public static final String VIEW_GROUP = "hasAuthority('read:view_group')";

}
