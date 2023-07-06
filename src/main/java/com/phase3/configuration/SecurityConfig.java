
 package com.phase3.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.phase3.service.CustomUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

 @Configuration
@EnableWebSecurity
public class SecurityConfig {
	 
	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	@Autowired
	CustomUserDetailService customUserDetailService;

	  
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/shop/**", "register", "/h2-console/**").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("password"))
                .oauth2Login(login -> login
                        .loginPage("/login")
                        .successHandler(googleOAuth2SuccessHandler))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(withDefaults())
                .csrf(c->c.disable());
        
		http.headers(headers -> headers.frameOptions(withDefaults()).disable());

		return http.build();
	}
	
	 	        
	        @Bean
	    	public BCryptPasswordEncoder passwordEncoder() {
	    		return new BCryptPasswordEncoder();
	    	}

	    	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(customUserDetailService);  
	    	}

	    	public void configure(WebSecurity web) throws Exception {
	    		
	    		web.ignoring().requestMatchers("/resources/**","/static/**","/images/**","/productImages/**","/css/**","/js/**","/h2-console/**");
	    	}
	}


	        
	       
	        
	        
	        
	       
	            
	    
  