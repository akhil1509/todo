package com.todo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	
	@Bean
	  PasswordEncoder passwordEncoder(){
	       return new BCryptPasswordEncoder();
	   }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests((e) -> {
//			e.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
//			e.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
//			e.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
//			e.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//			e.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//			e.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");
			e.requestMatchers("/api/auth/**").permitAll();
			e.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
        return http.build();
	}

//	 @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//	        return configuration.getAuthenticationManager();
//	    }
	@Bean
   public UserDetailsService userDetailsService(){

       UserDetails ramesh = User.builder()
               .username("ramesh")
               .password(passwordEncoder().encode("password"))
               .roles("USER")
               .build();

       UserDetails admin = User.builder()
               .username("admin")
               .password(passwordEncoder().encode("admin"))
               .roles("ADMIN")
               .build();

       return new InMemoryUserDetailsManager(ramesh, admin);
   }
	
   
}
