/**
 * 
 */
package edu.bu.met673.usermanagement.utils;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 */
public final class TokenUtils {
	
	private TokenUtils() {}
	
	
	public static String getProviderIdFromToken() {
		return getToken();
	}
	
	public static List<String> getClaims(){
		try {
			return SecurityContextHolder.getContext()
					.getAuthentication()
					.getAuthorities().stream().map(authority->authority.getAuthority()).toList();
		
		}catch(Exception ex) {
			return List.of();
		}
	}
	
	public static String getToken() {
		try{
			var requestContextHolder = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if(Objects.nonNull(requestContextHolder)) {
				return requestContextHolder.getRequest().getHeader("Authorization");
			}
		}catch(Exception ex) {}
		
		return "";
	}

}
