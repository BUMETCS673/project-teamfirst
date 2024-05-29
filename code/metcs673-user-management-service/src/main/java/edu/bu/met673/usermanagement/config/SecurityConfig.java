/**
 * 
 */
package edu.bu.met673.usermanagement.config;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import io.micrometer.observation.annotation.Observed;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(c -> c
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> 
                	oauth2ResourceServer.jwt(withDefaults())
                						.accessDeniedHandler(accessDeniedHandler())
                						.authenticationEntryPoint(authenticationEntryPoint()))
                .build();
    }
    
    @Observed
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String error = "{ \"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\" }";
            log.error(error);
            response.getOutputStream().println("{ \"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\" }");
        };
    }

    @Observed
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String error = "{ \"error\": \"Forbidden\", \"message\": \"" + accessDeniedException.getMessage() + "\" }";
            log.error(error);
            response.getOutputStream().println(error);
        };
    }
    
    @Bean
    AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> {
        	log.info("unauthorizedEntryPoint :{}", authException);
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access!");};
    }

    @Bean
    AccessDeniedHandler forbiddenEntryPoint() {
        return (request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN, "You don't have permission to access this resource!");
    }

}
