/**
 * 
 */
package edu.bu.met673.usermanagement.auth0.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 
 */
@FeignClient(name="auth0", url="https://dev-v4utikpr1mrs4k05.us.auth0.com")
public interface Auth0Client {
	
	@GetMapping("/userinfo")
	Auth0User getUserProfile(@RequestHeader("Authorization") String bearerToken);
}
