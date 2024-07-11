package com.chenrique.spring_secury.basic;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(csrf -> csrf.disable());
		http.httpBasic(Customizer.withDefaults());
		http.headers(headers -> headers.frameOptions(f -> f.sameOrigin()));
		return http.build();
	}

	/*
	 * @Bean UserDetailsService userDetailsService() {
	 * 
	 * var user =
	 * User.withUsername("user").password("{noop}12345").roles("USER").build(); var
	 * admin =
	 * User.withUsername("admin").password("{noop}adm123").roles("ADMIN").build();
	 * 
	 * return new InMemoryUserDetailsManager(user, admin); }
	 */

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(
						org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}

	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {

		var user = User.withUsername("user").password("{noop}12345").roles("USER").build();
		var admin = User.withUsername("admin").password("{noop}adm123").roles("ADMIN").build();
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}
}
