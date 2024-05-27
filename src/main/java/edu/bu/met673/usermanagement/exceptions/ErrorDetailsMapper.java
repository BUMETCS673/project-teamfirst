/**
 * 
 */
package edu.bu.met673.usermanagement.exceptions;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * @author ajord
 *
 */
@Configuration
@ConfigurationProperties(prefix = "error")
@PropertySource({"classpath:error/error-messages.properties"})
@Data
public class ErrorDetailsMapper {
	
	private Map<String, ErrorDetails> auth;

}
