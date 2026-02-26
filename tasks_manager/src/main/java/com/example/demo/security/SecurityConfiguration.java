package com.example.demo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	 @Autowired
	    private DatabaseUserDetailsService databaseUserDetailsService;

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
    throws Exception {
      http
      	.csrf(AbstractHttpConfigurer::disable)
      	.cors(Customizer.withDefaults())
      	.authorizeHttpRequests(requests -> requests
	        .requestMatchers("/user").hasAuthority("USER")
	        .requestMatchers("/admin").hasAuthority("ADMIN")
	        .requestMatchers("/users/login").permitAll()
	        .requestMatchers("/users/register").permitAll()
	        .anyRequest().authenticated());
      return http.httpBasic(Customizer.withDefaults()).build();
	}

    @Bean
    @SuppressWarnings("deprecation")
    public DaoAuthenticationProvider authenticationProvider() {
    	
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(databaseUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowedOrigins(List.of("http://localhost:3001"));
    	configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    	configuration.setAllowCredentials(true);
    	configuration.addAllowedHeader("*");
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }
}
